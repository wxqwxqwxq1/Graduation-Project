<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车辆编号" prop="vehicleId">
        <el-input
          v-model="queryParams.vehicleId"
          placeholder="请输入车辆编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车主编号" prop="ownerId">
        <el-input
          v-model="queryParams.ownerId"
          placeholder="请输入车主编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车牌照" prop="licensePlate">
        <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输入车牌照"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆类型" prop="vehicleType">
        <el-input
          v-model="queryParams.vehicleType"
          placeholder="请输入车辆类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆型号" prop="vehicleModel">
        <el-input
          v-model="queryParams.vehicleModel"
          placeholder="请输入车辆型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆颜色" prop="vehicleColor">
        <el-input
          v-model="queryParams.vehicleColor"
          placeholder="请输入车辆颜色"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆状态" prop="vehiclePermit">
        <el-select v-model="queryParams.vehiclePermit" placeholder="请选择车辆状态" clearable @change="handleQuery">
          <el-option label="允许" value="1"></el-option>
          <el-option label="禁止" value="0"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['fvehicles:fvehicle:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['fvehicles:fvehicle:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['fvehicles:fvehicle:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['fvehicles:fvehicle:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="fvehicleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="车辆编号" align="center" prop="vehicleId" />
      <el-table-column label="车牌照" align="center" prop="licensePlate" />
      <el-table-column label="车辆类型 " align="center" prop="vehicleType" />
      <el-table-column label="车辆型号" align="center" prop="vehicleModel" />
      <el-table-column label="车辆颜色" align="center" prop="vehicleColor" />
<!--      <el-table-column label="车辆许可" align="center" prop="vehiclePermit" />-->
<!--      使用switch开关来表示车辆许可-->
      <el-table-column label="车辆许可" align="center" prop="vehiclePermit">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.vehiclePermit"
            active-text="开"
            inactive-text="关"
            @change="handlePermissionChange(scope.row)"
          />
        </template>
      </el-table-column>

      <!-- 车主列 -->
      <el-table-column prop="ownerId" label="车主编号">
        <template slot-scope="scope">
          <a href="javascript:;" @click="goToOwner(scope.row.ownerId)">{{ scope.row.ownerId }}</a>
        </template>
      </el-table-column>
<!--      <el-table-column label="车主编号" align="center" prop="ownerId" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['fvehicles:fvehicle:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['fvehicles:fvehicle:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改车辆对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车牌照" prop="licensePlate">
          <el-input v-model="form.licensePlate" placeholder="请输入车牌照" />
        </el-form-item>
        <el-form-item label="车辆类型" prop="vehicleType">
          <el-input v-model="form.vehicleType" placeholder="请输入车辆类型" />
        </el-form-item>
        <el-form-item label="车辆型号" prop="vehicleModel">
          <el-input v-model="form.vehicleModel" placeholder="请输入车辆型号" />
        </el-form-item>
        <el-form-item label="车辆颜色" prop="vehicleColor">
          <el-input v-model="form.vehicleColor" placeholder="请输入车辆颜色" />
        </el-form-item>
        <el-form-item label="车辆许可"  prop="vehiclePermit">
          <template slot-scope="scope">
            <el-switch
              v-model="form.vehiclePermit"
              active-text="开"
              inactive-text="关"
            />
          </template>
        </el-form-item>
        <!-- 车主编号输入框 -->
        <el-form-item label="车主编号" prop="ownerId">
          <el-input v-model="ownerForm.ownerId" placeholder="请输入车主编号" @blur="onOwnerNumberInput"></el-input>
        </el-form-item>
        <!-- 车主姓名下拉选择框 -->
        <el-form-item label="车主姓名" prop="ownerName">
          <el-select
            v-model="ownerForm.ownerName"
            remote-show-suffix
          filterable
          remote
          reserve-keyword
          placeholder="请输入车主姓名"
          :remote-method="remoteMethod"
          :loading="isLoading"
          @change="onOwnerNameChange"
          >
          <el-option
            v-for="item in ownerList"
            :key="item.ownerId"
            :label="item.ownerName"
            :value="item.ownerId"
          ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

import {getOwner, listOwner} from "@/api/fvehicles/owner";
import {addFvehicle, delFvehicle, getFvehicle, listFvehicle, updateFvehicle} from "@/api/fvehicles/fvehicle";

export default {
  name: "Fvehicle",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 车辆表格数据
      fvehicleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vehicleId: null,
        licensePlate: null,
        vehicleType: null,
        vehicleModel: null,
        vehicleColor: null,
        vehiclePermit: null,
        ownerId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        licensePlate: [
          { required: true, message: "车牌照不能为空", trigger: "blur" }
        ],
        //   车主编号，车主姓名必须输入
        ownerId: [
          { required: true, message: "车主编号不能为空", trigger: "blur" }
        ],
      },
    //   车主列表
      ownerForm: {
        ownerId: '', // 车主编号
        ownerName: '', // 车主姓名
      },
      ownerList: [], // 车主列表数据
      isLoading: false,
      // 传来参数
      queryFvehicle: this.$route.query.vehicleId || null,
    }
  },
  created() {
    this.getList();
    // this.searchOwner();
    console.log('Created:', this.$route.query);
  },
  watch: {
    '$route.query.vehicleId': {
      handler(newVehicleId, oldVehicleId) {
        console.log(`Vehicle ID changed from ${oldVehicleId} to ${newVehicleId}`);
        this.queryFvehicle = newVehicleId;
        this.showThisData();
      },
      $route(to, from) {
        // 调用方法重新获取数据
        this.getList();
      },
      immediate: true
    }
  },
  // watch: {
  //   '$route.query.vehicleId'(newFvehicle, oldFvehicle) {
  //     console.log(`查询参数queryFvehicle变化了，新的值为：${newFvehicle}，旧的值为：${oldFvehicle}`);
  //     this.queryFvehicle = newFvehicle;
  //     console.log(this.queryFvehicle);
  //     // this.fetchOwnerDetails(newOwnerId); // 调用一个方法来根据新的ownerId获取数据
  //     //   调用方法使ownerId这一行高亮
  //     this.showThisData();
  //   }
  // },
  methods: {
    // 使ownerId这一行高亮----暂时不用
    showThisData() {
      console.log(this.queryFvehicle);
      //   调用查询方法，根据ownerId查询数据
      //   清空查询条件
      this.queryParams.vehicleId = this.queryFvehicle;
      this.handleQuery();
    },
    // 车主姓名输入时触发
    remoteMethod(query) {
      console.log('车主姓名输入', query);
      // 根据输入查询车主信息
      if (query !== '') {
        // 定义一个变量来保存查询参数
        let params = {
          pageNum: 1,
          pageSize: 10,
          ownerName: query
        };
        this.isLoading = true;
        // 发送请求到后端API查询车主数据
        // 假设 searchOwners 是您用来从数据库中搜索数据的方法
        listOwner(params).then(response => {
          this.ownerList = response.rows; // 假设返回的数据是一个数组
          this.isLoading = false;
          //   打印
          console.log('车主列表', this.ownerList);
          // console.log(this.ownerList);
        }).catch(() => {
          this.isLoading = false;
        });
      } else {
        this.ownerList = [];
      }
    },
    // 当输入车主编号时自动填充车主姓名
    onOwnerNumberInput() {
      let value = this.ownerForm.ownerId;
      // 打印
      console.log('车主编号输入', value);
      // 根据车主编号查询对应的车主姓名并更新表单
      getOwner(value).then(response => {
        // 打印
        // console.log(response.data);
        // 暂存车主信息
        let return_owner = response.data;
        //   如果结果不为空，则更新车主姓名
        if (return_owner !== null && return_owner !== undefined) {
          this.ownerForm.ownerName = return_owner.ownerName;
          this.form.ownerName = return_owner.ownerName;
          //   车主编号用0填充到6位
          this.form.ownerId = return_owner.ownerId.toString().padStart(6, "0");
          this.ownerForm.ownerId = return_owner.ownerId.toString().padStart(6, "0");
          // console.log(this.form);
        }
        //   结果为空则提示
        else {
          this.$message.error('未找到车主编号为' + value + '的车主');
        }
      });
    },
    // 当选择车主姓名时自动填充车主编号
    onOwnerNameChange(value) {
      // 打印
      // console.log('车主姓名选择', value);
      // console.log(this.ownerList);
      // 当车主姓名选择时触发
      // 根据选择的车主姓名查找相应的编号
      // 车主编号用0填充到6位
      value = value.toString().padStart(6, "0");
      this.form.ownerId = value;
      this.ownerForm.ownerId = value;
      // console.log(this.form);
    },
    // 表单某项为空时，填充默认值
    fillDefaultValues() {
      // 检查每个字段，如果为空，则填充默认值
      if (!this.form.vehicleModel) this.form.vehicleModel = '无';
      if (!this.form.vehicleType) this.form.vehicleType = '无';
      if (!this.form.vehicleColor) this.form.vehicleColor = '无';
      if (!this.form.vehicleId) this.form.drivingLicenseNumber = 0;
      // 对于其他类型的字段，您可能需要根据实际情况进行判断
      // 将许可转换为数字
      this.form.vehiclePermit = this.form.vehiclePermit ? 1 : 0;
      console.log("提交信息" + this.form);
      // if (!this.form.vehiclePermit) this.form.vehiclePermit = true; // 假设默认为true
    },
    // 车辆许可开关变化时触发
    handlePermissionChange(row) {
      // 这里可以处理开关变化后的逻辑，比如更新数据库
      // 修改车辆许可状态
      row.vehiclePermit = row.vehiclePermit ? 1 : 0;
      updateFvehicle(row).then(response => {
        this.$modal.msgSuccess("车辆许可已更改");
        //   重新加载列表
        this.getList();
      });
    },
    // 跳转到车主详情
    goToOwner(ownerId) {
      // 这里可以处理点击车主名字后的跳转逻辑，比如使用Vue Router进行路由跳转
      this.$router.push({ name: 'Owner' , query: { ownerId }} );
      // this.$router.push(`/owner/${ownerId}`);
      console.log('跳转到车主详情', ownerId);
    },

    /** 查询车辆列表 */
    getList() {
      this.loading = true;
      listFvehicle(this.queryParams).then(response => {
        // 打印
        // console.log(response);
        this.fvehicleList = response.rows;
        // console.log(this.fvehicleList);
        // console.log(response);
        this.fvehicleList = response.rows.map(item => {
          // 确保 vehiclePermit 是 1 或 0
          // item.vehiclePermit = item.vehiclePermit ? 1 : 0;
          item.vehiclePermit = item.vehiclePermit === 1;
          // 车辆编号用0填充到6位
          item.vehicleId = item.vehicleId.toString().padStart(6, "0");
          // 车主编号用0填充到6位
          item.ownerId = item.ownerId.toString().padStart(6, "0");
          return item;
        });
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.ownerForm = {
        ownerId: '',
        ownerName: ''
      };
      this.form = {
        vehicleId: null,
        licensePlate: null,
        vehicleType: null,
        vehicleModel: null,
        vehicleColor: null,
        vehiclePermit: null,
        ownerId: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.vehicleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加车辆";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      // 复制ownerID到ownerForm
      this.ownerForm.ownerId = row.ownerId;
      const vehicleId = row.vehicleId || this.ids
      getFvehicle(vehicleId).then(response => {
        this.form = response.data;
        this.form.vehiclePermit = this.form.vehiclePermit === 1;
        this.open = true;
        this.title = "修改车辆";
      });
    },
    /** 提交按钮 */
    submitForm() {
      // 表单校验
      this.fillDefaultValues();
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.vehicleId != null) {
            updateFvehicle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFvehicle(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const vehicleIds = row.vehicleId || this.ids;
      this.$modal.confirm('是否确认删除车辆编号为"' + vehicleIds + '"的数据项？').then(function() {
        return delFvehicle(vehicleIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('fvehicles/fvehicle/export', {
        ...this.queryParams
      }, `fvehicle_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
