<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="优惠券编号" prop="couponId">
        <el-input
          v-model="queryParams.couponId"
          placeholder="请输入优惠券编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优惠券代码" prop="couponCode">
        <el-input
          v-model="queryParams.couponCode"
          placeholder="请输入优惠券代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="折扣金额" prop="discountAmount">
        <el-input
          v-model="queryParams.discountAmount"
          placeholder="请输入折扣金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="折扣百分比" prop="discountPercentage">
        <el-input
          v-model="queryParams.discountPercentage"
          placeholder="请输入折扣百分比"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最低消费金额" prop="minimumPurchaseAmount">
        <el-input
          v-model="queryParams.minimumPurchaseAmount"
          placeholder="请输入最低消费金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用次数" prop="usageLimit">
        <el-input
          v-model="queryParams.usageLimit"
          placeholder="请输入使用限制次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否激活" prop="isActive">
        <el-select v-model="queryParams.isActive" placeholder="请选择优惠券状态" clearable @change="handleQuery">
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
          v-hasPermi="['manage:coupons:add']"
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
          v-hasPermi="['manage:coupons:edit']"
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
          v-hasPermi="['manage:coupons:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manage:coupons:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="couponsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="优惠券编号" align="center" prop="couponId" />
      <el-table-column label="优惠券代码" align="center" prop="couponCode" />
      <el-table-column label="优惠券描述" align="center" prop="description" />
      <el-table-column label="折扣金额" align="center" prop="discountAmount" />
      <el-table-column label="折扣百分比" align="center" prop="discountPercentage" />
      <el-table-column label="最低消费金额" align="center" prop="minimumPurchaseAmount" />
      <el-table-column label="使用次数" align="center" prop="usageLimit" />
      <el-table-column label="有效起始日期" align="center" prop="validFrom" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.validFrom, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="有效结束日期" align="center" prop="validTo" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.validTo, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否激活" align="center" prop="isActive">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.isActive"
            active-text="是"
            inactive-text="否"
            @change="handlePermissionChange(scope.row)"
          />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:coupons:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:coupons:remove']"
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

    <!-- 添加或修改优惠券表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="优惠券代码" prop="couponCode">
          <el-input v-model="form.couponCode" placeholder="请输入优惠券代码" />
        </el-form-item>
        <el-form-item label="优惠券描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="折扣金额" prop="discountAmount">
          <el-input v-model="form.discountAmount" placeholder="请输入折扣金额" />
        </el-form-item>
        <el-form-item label="折扣百分比" prop="discountPercentage">
          <el-input v-model="form.discountPercentage" placeholder="请输入折扣百分比" />
        </el-form-item>
        <el-form-item label="最低消费金额" prop="minimumPurchaseAmount">
          <el-input v-model="form.minimumPurchaseAmount" placeholder="请输入最低消费金额" />
        </el-form-item>
        <el-form-item label="使用限制次数" prop="usageLimit">
          <el-input v-model="form.usageLimit" placeholder="请输入使用限制次数" />
        </el-form-item>
        <el-form-item label="起始日期" prop="validFrom">
          <el-date-picker clearable
                          v-model="form.validFrom"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择起始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束日期" prop="validTo">
          <el-date-picker clearable
                          v-model="form.validTo"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请有效结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否激活"  prop="isActive">
          <template slot-scope="scope">
            <el-switch
              v-model="form.isActive"
              active-text="是"
              inactive-text="否"
            />
          </template>
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
import { listCoupons, getCoupons, delCoupons, addCoupons, updateCoupons } from "@/api/manage/coupons";

export default {
  name: "Coupons",
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
      // 优惠券表表格数据
      couponsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        couponId: null,
        couponCode: null,
        description: null,
        discountAmount: null,
        discountPercentage: null,
        minimumPurchaseAmount: null,
        usageLimit: null,
        validFrom: null,
        validTo: null,
        isActive: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      //   优惠券代码为必须项
        couponCode: [
          { required: true, message: "请输入优惠券代码", trigger: "blur" }
        ],
      //   开始时间和结束时间是必须
        validFrom: [
          { required: true, message: "请选择有效起始日期", trigger: "change" }
        ],
        validTo: [
          { required: true, message: "请选择有效结束日期", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 许可开关变化时触发
    handlePermissionChange(row) {
      // 这里可以处理开关变化后的逻辑，比如更新数据库
      // 修改车辆许可状态
      row.isActive = row.isActive ? 1 : 0;
      updateCoupons(row).then(response => {
        this.$modal.msgSuccess("优惠券状态已更改");
        //   重新加载列表
        this.getList();
      });
    },
    /** 查询优惠券表列表 */
    getList() {
      this.loading = true;
      listCoupons(this.queryParams).then(response => {
        this.couponsList = response.rows;
        this.total = response.total;
        console.log(this.couponsList);
        this.couponsList = response.rows.map(item => {
          // 确保 isActive 是 1 或 0
          // item.isActive = item.isActive ? 1 : 0;
          item.isActive = item.isActive === 1;
          // 车辆编号用0填充到6位
          item.couponId = item.couponId.toString().padStart(6, "0");
          return item;
        });
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
        couponId: null,
        couponCode: null,
        description: null,
        discountAmount: null,
        discountPercentage: null,
        minimumPurchaseAmount: null,
        usageLimit: null,
        validFrom: null,
        validTo: null,
        isActive: null,
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
      this.ids = selection.map(item => item.couponId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加优惠券表";
    },
    // 表单某项为空时，填充默认值
    fillDefaultValues() {
      // 检查每个字段，如果为空，则填充默认值
      if (!this.form.description) this.form.description = '无';
      if (!this.form.discountAmount) this.form.discountAmount = 0;
      if (!this.form.discountPercentage) this.form.discountPercentage = 0;
      if (!this.form.minimumPurchaseAmount) this.form.minimumPurchaseAmount = 0;
      // 设置默认使用次数为100
      if (!this.form.usageLimit) this.form.usageLimit = 100;
      // 对于其他类型的字段，您可能需要根据实际情况进行判断
      // 将许可转换为数字
      this.form.isActive = this.form.isActive ? 1 : 0;
      // if (!this.form.vehiclePermit) this.form.vehiclePermit = true; // 假设默认为true
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const couponId = row.couponId || this.ids
      getCoupons(couponId).then(response => {
        this.form = response.data;
        this.form.isActive = this.form.isActive === 1;
        this.open = true;
        this.title = "修改优惠券表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.fillDefaultValues();
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.couponId != null) {
            updateCoupons(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCoupons(this.form).then(response => {
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
      const couponIds = row.couponId || this.ids;
      this.$modal.confirm('是否确认删除优惠券表编号为"' + couponIds + '"的数据项？').then(function() {
        return delCoupons(couponIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/coupons/export', {
        ...this.queryParams
      }, `coupons_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
