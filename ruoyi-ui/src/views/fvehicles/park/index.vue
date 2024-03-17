<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="车牌号" prop="licensePlate">
        <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输入车牌号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆编号" prop="vehicleId">
        <el-input
          v-model="queryParams.vehicleId"
          placeholder="请输入车辆编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车主姓名 " prop="ownerName">
        <el-input
          v-model="queryParams.ownerName"
          placeholder="请输入车主姓名 "
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
      <el-form-item label="车主电话" prop="ownerPhone">
        <el-input
          v-model="queryParams.ownerPhone"
          placeholder="请输入车主电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车库位置 " prop="garageLocation">
        <el-input
          v-model="queryParams.garageLocation"
          placeholder="请输入车库位置 "
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车库编号" prop="garageId">
        <el-input
          v-model="queryParams.garageId"
          placeholder="请输入车库编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['fvehicles:park:add']"
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
          v-hasPermi="['fvehicles:park:edit']"
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
          v-hasPermi="['fvehicles:park:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['fvehicles:park:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="parkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="associationId" />
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="车辆型号" align="center" prop="vehicleModel" />
      <el-table-column label="车主姓名 " align="center" prop="ownerName" />
      <el-table-column label="车主电话" align="center" prop="ownerPhone" />
      <el-table-column label="车库位置 " align="center" prop="garageLocation" />
      <el-table-column label="车库类型" align="center" prop="garageType" >
        <template slot-scope="scope">
          {{ getTypeText(scope.row.garageType) }}
        </template>
      </el-table-column>
      <el-table-column label=" 开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车主编号" align="center" prop="ownerId" >
        <template slot-scope="scope">
          <a href="javascript:;" @click="goToOwner(scope.row.ownerId)">{{ scope.row.ownerId }}</a>
        </template>
      </el-table-column>
      <el-table-column label="车库编号" align="center" prop="ownerId" >
        <template slot-scope="scope">
          <a href="javascript:;" @click="goToGarage(scope.row.garageId)">{{ scope.row.garageId }}</a>
        </template>
      </el-table-column>
      <el-table-column label="车辆编号" align="center" prop="vehicleId" >
        <template slot-scope="scope">
          <a href="javascript:;" @click="goToVehicle(scope.row.vehicleId)">{{ scope.row.vehicleId }}</a>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['fvehicles:park:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['fvehicles:park:remove']"
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

<!--    添加框-->
    <el-dialog :title="title" :visible.sync="AddOpen" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- 使用远程搜索的下拉框 -->
        <el-form-item label="车主编号" prop="ownerId">
          <el-select
            v-model="form.ownerId"
            filterable
            remote
            placeholder="请输入车主编号"
            :loading="isLoading"
            :remote-method="querySearchOwner"
            @change="onSelectChange">
            <el-option
              v-for="item in filteredOwner"
              :key="item.value"
              :label="item.value"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车主姓名" prop="ownerName">
          <el-input v-model="form.ownerName" :readonly="true" />
        </el-form-item>
        <el-form-item label="车主电话" prop="ownerPhone">
          <el-input v-model="form.ownerPhone" :readonly="true" />
        </el-form-item>
        <!-- 使用下拉框 -->
        <el-form-item label="车牌号" prop="licensePlate">
          <el-select v-model="form.licensePlate" placeholder="请选择车牌号" @change="onLicensePlateChange">
            <el-option
              v-for="item in licensePlates"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="车辆型号" prop="vehicleModel">
          <el-input v-model="form.vehicleModel" :readonly="true" />
        </el-form-item>
        <!-- 使用远程搜索的下拉框 -->
        <el-form-item label="车库位置" prop="garageLocation">
          <el-select
            v-model="form.garageLocation"
            filterable
            remote
            placeholder="请选择固定车库位置"
            :loading="isLoading"
            :remote-method="querySearchGarageLocation"
            @change="onGarageLocationChange">
            <el-option
              v-for="item in filteredGarageLocation"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable
                          v-model="form.startTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable
                          v-model="form.endTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确定</el-button>
        <el-button @click="cancel">取消</el-button>
      </div>
    </el-dialog>

<!--    修改框，-->
    <el-dialog :title="title" :visible.sync="EditOpen" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车主编号" prop="ownerId">
          <el-input v-model="form.ownerId" :readonly="true" />
        </el-form-item>
        <el-form-item label="车主姓名" prop="ownerName">
          <el-input v-model="form.ownerName" :readonly="true" />
        </el-form-item>
        <el-form-item label="车主电话" prop="ownerPhone">
          <el-input v-model="form.ownerPhone" :readonly="true" />
        </el-form-item>
        <el-form-item label="车牌号" prop="licensePlate">
          <el-input v-model="form.licensePlate" :readonly="true" />
        </el-form-item>
        <el-form-item label="车辆型号" prop="vehicleModel">
          <el-input v-model="form.vehicleModel" :readonly="true" />
        </el-form-item>
        <!-- 使用远程搜索的下拉框 -->
        <el-form-item label="车库位置" prop="garageLocation">
          <el-select
            v-model="form.garageLocation"
            filterable
            remote
            placeholder="请选择固定车库位置"
            :loading="isLoading"
            :remote-method="querySearchGarageLocation"
            @change="onGarageLocationChange">
            <el-option
              v-for="item in filteredGarageLocation"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable
                          v-model="form.startTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable
                          v-model="form.endTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确定</el-button>
        <el-button @click="cancel">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listPark,
  getPark,
  delPark,
  addPark,
  updatePark,
  querySearchLicensePlate,
  querySearchOwner, querySearchLicensePlateInfo, querySearchGarageLocation
} from "@/api/fvehicles/park";

export default {
  name: "Park",
  data() {
    return {
      isLoading: false,
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
      // 车位表格数据
      parkList: [],
      // 弹出层标题
      title: "",
      // 是否显示编辑弹出层
      EditOpen: false,
      // 是否显示添加弹出层
      AddOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        associationId: null,
        licensePlate: null,
        vehicleModel: null,
        vehicleId: null,
        ownerName: null,
        ownerId: null,
        ownerPhone: null,
        garageLocation: null,
        garageId: null,
        garageType: null,
        startTime: null,
        endTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        ownerId: [
          { required: true, message: "车主编号不能为空", trigger: "blur" }
        ],
        licensePlate: [
          { required: true, message: "车牌号不能为空", trigger: "blur" }
        ],
        garageLocation: [
          { required: true, message: "车库位置不能为空", trigger: "blur" }
        ],
      },
      // 下拉框数据--车牌号
      licensePlates: [],
      // 下拉框数据--车主
      filteredOwner: [],
    //   下拉框数据--车库位置
      filteredGarageLocation: []
    };
  },
  created() {
    this.getList();
  },
  watch: {
    'form.ownerId': function (newVal, oldVal) {
      if (newVal) {
        // 如果新的车主编号存在，则加载该车主的车牌号
        this.loadLicensePlates(newVal);
      } else {
        // 如果车主编号不存在，清空车牌号选择器的选项
        this.licensePlates = [];
      }
    }
  },
  methods: {
    // 跳转到车主详情
    goToOwner(ownerId) {
      this.$router.push({ name: 'Owner' , query: { ownerId }} );
      // this.$router.push(`/owner/${ownerId}`);
      console.log('跳转到车主详情', ownerId);
    },
    // 跳转到车库详情
    goToGarage(garageId) {
      this.$router.push({ name: 'Garage' , query: { garageId }} );
      // this.$router.push(`/garage/${garageId}`);
      console.log('跳转到车库详情', garageId);
    },
    // 跳转到车辆详情
    goToVehicle(vehicleId) {
      this.$router.push({ name: 'FVehicle' , query: { vehicleId }} );
      // this.$router.push({ path: '/fvehicles/fvehicle?vehicleId='+vehicleId }); // 通过路径跳转
      console.log('跳转到车辆详情', vehicleId);
    },
    // 远程搜索用户
    querySearchOwner(queryString) {
      if (queryString !== '') {
        this.isLoading = true; // 假设你有一个isLoading的data属性来显示加载状态
        // fetchData是一个假设的方法，用于从后端获取数据
        querySearchOwner(queryString).then((data) => {
          this.filteredOwner = data.data.map(item => ({
            value: item.ownerId,
            label: item.ownerName,
            phone: item.ownerPhone // 假设后端返回的字段名是ownerPhone
          }));
          this.isLoading = false;
        }).catch(error => {
          console.error('Error during the search:', error);
          this.isLoading = false;
        });
      } else {
        this.filteredOwner = [];
      }
    },

    onSelectChange(value) {
      // 当选中某个选项时执行的方法
      // 可以在这里根据选中的值填充其他表单项
      this.form.ownerId = value;
      this.filteredOwner.forEach(item => {
        if (item.value === value) {
          this.form.ownerName = item.label;
          this.form.ownerPhone = item.phone; // 更新电话号码
        }
      });
    //   清空车牌号选择器的选项
      this.licensePlates = [];
    //   清空车库位置选择器的选项
      this.filteredGarageLocation = [];
    //   清空车辆信息
      this.form.licensePlate = null;
      this.form.vehicleModel = null;
      this.form.vehicleId = null;
      this.form.garageLocation = null;
      this.form.garageId = null;
      this.form.garageType = null;
      this.form.startTime = null;
      this.form.endTime = null;
    },
    // 远程搜索车牌号
    loadLicensePlates(ownerId) {
      // 假设 fetchLicensePlates 是一个从后端获取车牌号的方法
      querySearchLicensePlateInfo(ownerId).then((response) => {
        console.log('response:', response);
        this.licensePlates = response.rows.map(item => ({
          value: item.vehicleId, // 假设你有一个唯一标识车牌号的字段
          label: item.licensePlate, // 车牌号
          model: item.vehicleModel // 车辆型号
        }));
      }).catch(error => {
        console.error('Error fetching license plates:', error);
      });
    },
    onLicensePlateChange(value) {
      // 根据选中的车牌号从后端获取车辆信息，并填充到表单中
      this.licensePlates.forEach(item => {
        if (item.value === value) {
          this.form.vehicleId = item.value;
          this.form.licensePlate = item.label;
          this.form.vehicleModel = item.model;
        }
      });
    },

    // 远程搜索车库位置
    querySearchGarageLocation(queryString) {
      if (queryString !== '') {
        this.isLoading = true; // 假设你有一个isLoading的data属性来显示加载状态
        // fetchData是一个假设的方法，用于从后端获取数据
        querySearchGarageLocation(queryString).then((data) => {
          console.log('data:', data);
          this.filteredGarageLocation = data.data.map(item => ({
            value: item.garageId,
            label: item.location,
            type: item.type // 假设后端返回的字段名是type
          }));
          this.isLoading = false;
        }).catch(error => {
          console.error('Error during the search:', error);
          this.isLoading = false;
        });
      } else {
        this.filteredGarageLocation = [];
      }
    },
    onGarageLocationChange(value) {
      // 根据选中的车库位置从后端获取车库信息，并填充到表单中
      this.filteredGarageLocation.forEach(item => {
        if (item.value === value) {
          this.form.garageId = item.value;
          this.form.garageLocation = item.label;
          this.form.garageType = item.type;
        }
      });
    },
    // 在 methods 中添加一个方法来转换类型
    getTypeText(type) {
      // 打印
      // console.log('type:', type);
      return type === 'fixed' ? '固定' : '非固定';
    },
    /** 查询车位列表 */
    getList() {
      this.loading = true;
      listPark(this.queryParams).then(response => {
        this.parkList = response.rows;
        console.log('this.parkList:', this.parkList);
        // 车库编号用0填充到6位
        this.parkList.forEach(item => {
          item.associationId = item.associationId.toString().padStart(6, "0");
          item.garageId = item.garageId.toString().padStart(6, "0");
          item.ownerId = item.ownerId.toString().padStart(6, "0");
          item.vehicleId = item.vehicleId.toString().padStart(6, "0");
        });
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      // 判断是新增还是修改
      if(this.AddOpen){
        this.AddOpen = false;
      }else if(this.EditOpen){
        this.EditOpen = false;
      }
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        associationId: null,
        licensePlate: null,
        vehicleModel: null,
        vehicleId: null,
        ownerName: null,
        ownerId: null,
        ownerPhone: null,
        garageLocation: null,
        garageId: null,
        garageType: null,
        startTime: null,
        endTime: null
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
      this.ids = selection.map(item => item.associationId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.AddOpen = true;
      this.title = "添加车位";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.EditOpen = true;
      const associationId = row.associationId || this.ids
      getPark(associationId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车位";
      });
    },
    /** 提交按钮 */
    submitForm() {
      console.log(this.form);
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.associationId != null) {
            // 先检测车库是否为固定车库，如果是固定车库，则允许修改车库位置；否则提示选择固定车库
            if (this.form.garageType === 'non_fixed') {
              this.$modal.msgError("固定车辆不允许停放在非固定车库中");
            } else {
              updatePark(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.EditOpen = false;
                this.getList();
              });
            }
          } else {
            addPark(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.AddOpen = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const associationIds = row.associationId || this.ids;
      this.$modal.confirm('是否确认删除车位编号为"' + associationIds + '"的数据项？').then(function () {
        return delPark(associationIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('fvehicles/park/export', {
        ...this.queryParams
      }, `park_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
