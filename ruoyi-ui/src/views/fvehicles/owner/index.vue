<template>
  <div class="app-container">
<!--    查询-->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="auto" >
      <el-form-item label="车主编号" prop="ownerId">
        <el-input
          v-model="queryParams.ownerId"
          placeholder="请输入车主编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车主姓名" prop="ownerName">
        <el-input
          v-model="queryParams.ownerName"
          placeholder="请输入车主姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="ownerIdCard">
        <el-input
          v-model="queryParams.ownerIdCard"
          placeholder="请输入身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="ownerPhone">
        <el-input
          v-model="queryParams.ownerPhone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item  label="驾驶证编号" prop="drivingLicenseNumber">
        <el-input
          v-model="queryParams.drivingLicenseNumber"
          placeholder="请输入驾驶证编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="剩余车位" prop="remainSpaces">
        <el-input
          v-model="queryParams.remainSpaces"
          placeholder="请输入剩余车位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!--四个按钮-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['fvehicles:owner:add']"
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
          v-hasPermi="['fvehicles:owner:edit']"
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
          v-hasPermi="['fvehicles:owner:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['fvehicles:owner:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

<!--    显示数据-->
    <el-table v-loading="loading" :data="ownerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="车主编号" align="center" prop="ownerId" />
      <el-table-column label="车主姓名" align="center" prop="ownerName" />
      <el-table-column label="身份证号" align="center" prop="ownerIdCard" />
      <el-table-column label="联系电话" align="center" prop="ownerPhone" />
      <el-table-column label="地址" align="center" prop="ownerAddress" />
      <el-table-column label="驾驶证编号" align="center" prop="drivingLicenseNumber" />
      <el-table-column label="车位个数" align="center" prop="parkingSpaces" />
      <el-table-column label="已用车位" align="center" prop="usedSpaces" />
      <el-table-column label="剩余车位" align="center" prop="remainSpaces" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['fvehicles:owner:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['fvehicles:owner:remove']"
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

    <!-- 添加或修改车主对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车主姓名" prop="ownerName">
          <el-input v-model="form.ownerName" placeholder="请输入车主姓名" />
        </el-form-item>
        <el-form-item label="身份证号" prop="ownerIdCard">
          <el-input v-model="form.ownerIdCard" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="联系电话" prop="ownerPhone">
          <el-input v-model="form.ownerPhone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="地址" prop="ownerAddress">
          <el-input v-model="form.ownerAddress" placeholder="请输入地址" />
        </el-form-item>

        <el-form label-width="auto">
          <el-form-item label="驾驶证编号" prop="drivingLicenseNumber">
            <el-input v-model="form.drivingLicenseNumber" placeholder="请输入驾驶证编号" />
          </el-form-item>
        </el-form>

        <el-form-item label="车位个数" prop="parkingSpaces">
          <el-input v-model.number="form.parkingSpaces" placeholder="请输入车位个数" />
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
import { listOwner, getOwner, delOwner, addOwner, updateOwner } from "@/api/fvehicles/owner";

export default {
  name: "Owner",
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
      // 车主表格数据
      ownerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ownerId: null,
        ownerName: null,
        ownerIdCard: null,
        ownerPhone: null,
        drivingLicenseNumber: null,
        usedSpaces: null,
        remainSpaces: null
      },
      // 表单参数
      form: {
      },
      // 表单校验
      rules: {
        ownerName: [
          { required: true, message: '请输入车主姓名', trigger: 'blur' }
        ],
        ownerIdCard: [
          { required: true, message: '请输入身份证号', trigger: 'blur' },
          { min: 18, max: 18, message: '身份证号必须是18位', trigger: 'blur' }
        ],
        ownerPhone: [
          { required: true, message: '请输入联系电话', trigger: 'blur' },
          { min: 11, max: 11, message: '联系电话必须是11位', trigger: 'blur' }
        ],
        parkingSpaces: [
          { type: 'number', min: 0, message: '车位个数必须大于等于0', trigger: 'blur' }
        ]
      },
      // 查询参数ownerId
      queryOwnerId: this.$route.query.ownerId || null,
    };
  },
  watch: {
    '$route.query.ownerId'(newOwnerId, oldOwnerId) {
      console.log(`查询参数ownerId变化了，新的值为：${newOwnerId}，旧的值为：${oldOwnerId}`);
      this.queryOwnerId = newOwnerId;
      console.log(this.queryOwnerId);
      // this.fetchOwnerDetails(newOwnerId); // 调用一个方法来根据新的ownerId获取数据
    //   调用方法使ownerId这一行高亮
      this.showThisData();
    },
    $route(to, from) {
      // 调用方法重新获取数据
      this.getList();
    },
    immediate: true
  },
  created() {
    this.getList();
    console.log('Created:', this.$route.query);
  },
  methods: {
    // 使ownerId这一行高亮----暂时不用
    showThisData() {
      console.log(this.queryOwnerId);
    //   调用查询方法，根据ownerId查询数据
    //   清空查询条件

      this.queryParams.ownerId = this.queryOwnerId;
      this.handleQuery();
    },
    // 表单某项为空时，填充默认值
    fillDefaultValues() {
      // 检查每个字段，如果为空，则填充默认值
      if (!this.form.ownerIdCard) this.form.ownerIdCard = '无';
      if (!this.form.ownerAddress) this.form.ownerAddress = '无';
      if (!this.form.drivingLicenseNumber) this.form.drivingLicenseNumber = '无';
      // 对于数字类型的字段，您可能需要根据实际情况进行判断
      if (!this.form.parkingSpaces) this.form.parkingSpaces = 0; // 假设默认0个车位
    },
    /** 查询车主列表 */
    getList() {
      this.loading = true;
      listOwner(this.queryParams).then(response => {
        // 打印查询参数
        console.log(this.queryParams);
        this.ownerList = response.rows;
        // 车主编号用0填充到6位
        this.ownerList.forEach(item => {
          item.ownerId = item.ownerId.toString().padStart(6, "0");
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
      this.form = {
        ownerId: null,
        ownerName: null,
        ownerIdCard: null,
        ownerPhone: null,
        ownerAddress: null,
        drivingLicenseNumber: null,
        parkingSpaces: null,
        usedSpaces: null,
        remainSpaces: null
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
      this.ids = selection.map(item => item.ownerId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加车主";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ownerId = row.ownerId || this.ids
      getOwner(ownerId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车主";
      });
    },
    /** 提交按钮 */
    submitForm() {
      // 填充默认值
      this.fillDefaultValues();
      // 打印数据
      // console.log(this.form);
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.ownerId != null) {
            updateOwner(this.form).then(response => {
              console.log(response);
              this.$modal.msgSuccess(response.msg || "修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOwner(this.form).then(response => {
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
      const ownerIds = row.ownerId || this.ids;
      this.$modal.confirm('是否确认删除车主编号为"' + ownerIds + '"的数据项？').then(function() {
        return delOwner(ownerIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('fvehicles/owner/export', {
        ...this.queryParams
      }, `owner_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

