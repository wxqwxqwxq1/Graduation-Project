<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="价格编号" prop="pricingId">
        <el-input
          v-model="queryParams.pricingId"
          placeholder="请输入价格编号"
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
      <el-form-item label="车库位置描述" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入车库位置描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车库总车位数" prop="totalCapacity">
        <el-input
          v-model="queryParams.totalCapacity"
          placeholder="请输入车库总车位数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车位价格 " prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入车位价格 "
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="货币单位" prop="currency">
        <el-input
          v-model="queryParams.currency"
          placeholder="请输入货币单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录创建时间" prop="createdAt">
        <el-date-picker clearable
                        v-model="queryParams.createdAt"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择记录创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="记录创建时间" prop="updatedAt">
        <el-date-picker clearable
                        v-model="queryParams.updatedAt"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择记录创建时间">
        </el-date-picker>
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
          v-hasPermi="['manage:pricingGarage:add']"
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
          v-hasPermi="['manage:pricingGarage:edit']"
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
          v-hasPermi="['manage:pricingGarage:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manage:pricingGarage:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pricingGarageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="价格编号" align="center" prop="pricingId" />
      <el-table-column label="车库编号" align="center" prop="garageId" />
      <el-table-column label="车库位置描述" align="center" prop="location" />
      <el-table-column label="车库总车位数" align="center" prop="totalCapacity" />
      <el-table-column label="车库类型" align="center" prop="type" />
      <el-table-column label="车位价格 " align="center" prop="price" />
      <el-table-column label="货币单位" align="center" prop="currency" />
      <el-table-column label="记录创建时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="记录创建时间" align="center" prop="updatedAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:pricingGarage:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:pricingGarage:remove']"
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

    <!-- 添加或修改车库-价格视图对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="价格编号" prop="pricingId">
          <el-input v-model="form.pricingId" placeholder="请输入价格编号" />
        </el-form-item>
        <el-form-item label="车库编号" prop="garageId">
          <el-input v-model="form.garageId" placeholder="请输入车库编号" />
        </el-form-item>
        <el-form-item label="车库位置描述" prop="location">
          <el-input v-model="form.location" placeholder="请输入车库位置描述" />
        </el-form-item>
        <el-form-item label="车库总车位数" prop="totalCapacity">
          <el-input v-model="form.totalCapacity" placeholder="请输入车库总车位数" />
        </el-form-item>
        <el-form-item label="车位价格 " prop="price">
          <el-input v-model="form.price" placeholder="请输入车位价格 " />
        </el-form-item>
        <el-form-item label="货币单位" prop="currency">
          <el-input v-model="form.currency" placeholder="请输入货币单位" />
        </el-form-item>
        <el-form-item label="记录创建时间" prop="createdAt">
          <el-date-picker clearable
                          v-model="form.createdAt"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择记录创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="记录创建时间" prop="updatedAt">
          <el-date-picker clearable
                          v-model="form.updatedAt"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择记录创建时间">
          </el-date-picker>
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
import { listPricingGarage, getPricingGarage, delPricingGarage, addPricingGarage, updatePricingGarage } from "@/api/manage/pricingGarage";

export default {
  name: "PricingGarage",
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
      // 车库-价格视图表格数据
      pricingGarageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pricingId: null,
        garageId: null,
        location: null,
        totalCapacity: null,
        type: null,
        price: null,
        currency: null,
        createdAt: null,
        updatedAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        pricingId: [
          { required: true, message: "价格编号不能为空", trigger: "blur" }
        ],
        garageId: [
          { required: true, message: "车库编号不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "车库类型不能为空", trigger: "change" }
        ],
        createdAt: [
          { required: true, message: "记录创建时间不能为空", trigger: "blur" }
        ],
        updatedAt: [
          { required: true, message: "记录创建时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询车库-价格视图列表 */
    getList() {
      this.loading = true;
      listPricingGarage(this.queryParams).then(response => {
        this.pricingGarageList = response.rows;
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
        pricingId: null,
        garageId: null,
        location: null,
        totalCapacity: null,
        type: null,
        price: null,
        currency: null,
        createdAt: null,
        updatedAt: null
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
      this.ids = selection.map(item => item.pricingId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加车库-价格视图";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pricingId = row.pricingId || this.ids
      getPricingGarage(pricingId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车库-价格视图";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pricingId != null) {
            updatePricingGarage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPricingGarage(this.form).then(response => {
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
      const pricingIds = row.pricingId || this.ids;
      this.$modal.confirm('是否确认删除车库-价格视图编号为"' + pricingIds + '"的数据项？').then(function() {
        return delPricingGarage(pricingIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/pricingGarage/export', {
        ...this.queryParams
      }, `pricingGarage_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
