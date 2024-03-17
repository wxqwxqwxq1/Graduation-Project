<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车库编号" prop="garageId">
        <el-input
          v-model="queryParams.garageId"
          placeholder="请输入车库编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车库位置" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入车库位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总车位数" prop="totalCapacity">
        <el-input
          v-model="queryParams.totalCapacity"
          placeholder="请输入总车位数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车库状态" prop="isAvailable">
        <el-input
          v-model="queryParams.isAvailable"
          placeholder="请输入车库状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车库余量" prop="remainingCapacity">
        <el-input
          v-model="queryParams.remainingCapacity"
          placeholder="请输入车库余量"
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
          v-hasPermi="['manage:garage:add']"
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
          v-hasPermi="['manage:garage:edit']"
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
          v-hasPermi="['manage:garage:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manage:garage:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="garageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="车库编号" align="center" prop="garageId" />
      <el-table-column label="车库位置" align="center" prop="location" />
      <el-table-column label="总车位数" align="center" prop="totalCapacity" />
      <el-table-column label="车库类型" align="center" prop="type" >
        <template slot-scope="scope">
          {{ getTypeText(scope.row.type) }}
        </template>
      </el-table-column>
      <el-table-column label="车库状态" align="center" prop="isAvailable" >
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.isAvailable"
            active-text="开"
            inactive-text="关"
            @change="handlePermissionChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="车库余量" align="center" prop="remainingCapacity" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:garage:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:garage:remove']"
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

    <!-- 添加或修改 车库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车库位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入车库位置" />
        </el-form-item>
        <el-form-item label="总车位数" prop="totalCapacity">
          <el-input v-model="form.totalCapacity" placeholder="请输入总车位数" />
        </el-form-item>
        <el-form-item label="车库类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择车库类型">
            <el-option label="固定车库" value="fixed"></el-option>
            <el-option label="非固定车库" value="non-fixed"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车库状态"  prop="isAvailable">
          <template slot-scope="scope">
            <el-switch
              v-model="form.isAvailable"
              active-text="开"
              inactive-text="关"
            />
          </template>
        </el-form-item>
        <el-form-item label="车库余量" prop="remainingCapacity">
          <el-input
            v-model="form.remainingCapacity"
            placeholder="车库余量"
            readonly
          />
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
import { listGarage, getGarage, delGarage, addGarage, updateGarage } from "@/api/manage/garage";
import {updateFvehicle} from "@/api/fvehicles/fvehicle";

export default {
  name: "Garage",
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
      //  车库表格数据
      garageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        garageId: null,
        location: null,
        totalCapacity: null,
        type: null,
        isAvailable: null,
        remainingCapacity: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        location: [
          { required: true, message: "车库位置不能为空", trigger: "blur" }
        ],
        totalCapacity: [
          { required: true, message: "总车位数不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "车库类型不能为空", trigger: "change" }
        ],
      },
      // 查询参数ownerId
      queryGarageId: this.$route.query.garageId || null,
    };
  },
  watch: {
    '$route.query.garageId'(newGarageId, oldGarageId) {
      console.log(`查询参数ownerId变化了，新的值为：${newGarageId}，旧的值为：${oldGarageId}`);
      this.queryGarageId = newGarageId;
      console.log(this.queryGarageId);
      //   调用方法使ownerId这一行高亮
      this.showThisData();
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
      console.log(this.queryGarageId);
      //   调用查询方法，根据ownerId查询数据
      //   清空查询条件
      this.queryParams.garageId = this.queryGarageId;
      this.handleQuery();
    },
    // 车库许可开关变化时触发
    handlePermissionChange(row) {
      // 这里可以处理开关变化后的逻辑，比如更新数据库
      // 修改车库许可状态
      row.isAvailable = row.isAvailable ? 1 : 0;
      updateGarage(row).then(response => {
        this.$modal.msgSuccess("车库许可已更改");
        //   重新加载列表
        this.getList();
      });
    },
    // 在 methods 中添加一个方法来转换类型
    getTypeText(type) {
      return type === 'fixed' ? '固定' : '非固定';
    },
    /** 查询 车库列表 */
    getList() {
      this.loading = true;
      listGarage(this.queryParams).then(response => {
        this.garageList = response.rows;
        // 车库编号用0填充到6位
        this.garageList.forEach(item => {
          // 确保 isAvailable 是 true or false
          item.isAvailable = item.isAvailable === 1;
          item.garageId = item.garageId.toString().padStart(6, "0");
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
        garageId: null,
        location: null,
        totalCapacity: null,
        type: null,
        isAvailable: null,
        remainingCapacity: null
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
      this.ids = selection.map(item => item.garageId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加 车库";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const garageId = row.garageId || this.ids
      getGarage(garageId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改 车库";
      });
    },
    /** 提交按钮 */
    submitForm() {
      // 对表单数据进行处理
      // 车库状态转换为 1 or 0
      this.form.isAvailable = this.form.isAvailable ? 1 : 0;
      // 如果车库余量为空，则车库余量默认为总车位数
      if (this.form.remainingCapacity === null) {
        this.form.remainingCapacity = this.form.totalCapacity;
      }
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.garageId != null) {
            updateGarage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGarage(this.form).then(response => {
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
      const garageIds = row.garageId || this.ids;
      this.$modal.confirm('是否确认删除 车库编号为"' + garageIds + '"的数据项？').then(function() {
        return delGarage(garageIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/garage/export', {
        ...this.queryParams
      }, `garage_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
