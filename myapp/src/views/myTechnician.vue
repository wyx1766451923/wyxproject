<template>
  <!-- 表单 -->
  <el-form :inline="true" :model="formInline" class="demo-form-inline" ref="resetForm">
    <el-form-item label="技师名" prop="techname">
      <el-input v-model="formInline.techname" placeholder="请输入技师姓名" clearable />
    </el-form-item>
    <el-form-item label="商品类型" prop="protypeValue">
      <el-select v-model="formInline.protypeValue" class="m-2" placeholder="请选择商品类型" size="large">
        <el-option v-for="item in protypeOption" :key="item.id" :label="item.name" :value="item.id" />
      </el-select>
    </el-form-item>
    <el-form-item label="所属公司" prop="proBusValue">
      <el-select v-model="formInline.proBusValue" class="m-2" placeholder="请选择商品所属公司" size="large">
        <el-option v-for="item in proBusOption" :key="item.id" :label="item.busname" :value="item.id" />
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onReset">重置</el-button>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">搜索</el-button>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onAdd">新增</el-button>
    </el-form-item>
  </el-form>
  <!-- 表格 -->
  <el-table :data="tableData" style="width: 100%" ref="table" @row-dblclick="dbSelected">
    <el-table-column sortable prop="id" label="id" width="120" />
    <el-table-column prop="techname" label="姓名" width="180" />
    <el-table-column prop="techexplain" label="商品描述" width="180" show-overflow-tooltip />
    <el-table-column prop="busname" label="公司" width="120">
      <template #default="scope">
        {{ scope.row.business.busname }}
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" @click="handleEdit(scope.$index, scope.row)">修改</el-button>
        <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination small background v-model:current-page="currentPage" v-model:page-size="pageSize" :page-size="8"
    :page-sizes="[8, 15, 20]" layout="prev, pager, next,sizes" :total=newTotal class="mt-4"
    @current-change="handleCurrentChange" @size-change="handlePagesizeChange" />
  <!-- 模态框 -->
  <TecDialog @getTechinfo="getTechinfo" :data="data" :addOrEdit="addOrEdit" ref="sonEchoData"></TecDialog>
  <!-- 消息查看模态框 -->

  <el-dialog v-model="dialogTableVisible" title="技师预览">
    <el-card :body-style="{ padding: '0px' }">
      <img :src="imageurl"
        class="image" />
      <div style="padding: 14px">
        <span>服务项目</span>
        <div class="bottom">
          <div v-for="(item,index) in prodir" :key="index" class="proitem">
            {{item.proname}}
          </div>
        </div>
      </div>
    </el-card>
  </el-dialog>
</template>

<script setup>
import { ref, onMounted, reactive, nextTick  /*onBeforeUpdate*/ } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus'

import http from '../util/http.js'
import TecDialog from '../components/TecDialog.vue'
import { useShowStore } from '../stores/Show.js'
const store = useShowStore()
const currentPage = ref(1)
const pageSize = ref(8)
const sonEchoData = ref()
const table = ref()
let tableData = ref([])
let newTotal = ref(1)
import { getCtxImg } from '../util/common.js'
const ctxImg = getCtxImg()
const dialogTableVisible = ref(false)

// 获取产品列表
const getTechinfo = () => {
  http.post('/technician/getTechniciansPage', {
    page: currentPage.value,
    limit: pageSize.value
  })
    .then(res => {
      newTotal.value = res.data.data.total
      tableData.value = res.data.data.list
    })
}

const protypeOption = ref([])
const proBusOption = ref([])
// 获取产品类型数据
const getProtype = () => {
  http.get('/projecttype/getList')
    .then(res => {
      protypeOption.value = res.data.data
      // console.log(protypeOption.value)
    })
}
//获取公司数据
const getProBus = () => {
  http.get('/business/getList')
    .then(res => {
      proBusOption.value = res.data.data
      // console.log(proBusOption.value)
    })
}
const imgIsChanged = ref(false)
const isOwn = ref(null)
const data = ref()
const addOrEdit = ref(0)//默认为0，添加，1位修改
//修改数据
const handleEdit = (index, row) => {
  console.log(row)
  addOrEdit.value = 1
  getTechinfo()
  
  data.value = JSON.parse(JSON.stringify(row))
  // console.log(row)
  data.value.oldImageurl = row.imageurl
  imgIsChanged.value = true
  data.value.isOwn = isOwn.value
  store.TecdialogVisible = true
  nextTick(() => {

    sonEchoData.value.echoData()
  })


}
//删除数据
const handleDelete = (index, row) => {
  imgIsChanged.value = true

  data.value = JSON.parse(JSON.stringify(row))
  data.value.imgIsChanged = imgIsChanged.value
  data.value.isOwn = isOwn.value
  // console.log(data)
  ElMessageBox.confirm(
    '你确定要删除这条数据吗?',
    'Warning',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      http.post('/technician/delete', data.value, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(res => {
          // console.log(res)
          if (res.data.success) {
            ElMessage({
              type: 'success',
              message: '删除成功',
            })
            getTechinfo()
          }
          else {
            ElMessage({
              type: 'error',
              message: '删除失败',
            })
          }
        })

    })
    .catch(() => {
      // ElMessage({
      //   type: 'info',
      //   message: '取消删除',
      // })
    })


}
const handleCurrentChange = (index) => {
  currentPage.value = index
  getTechinfo()
}
const handlePagesizeChange = (index) => {
  pageSize.value = index
  getTechinfo()
}
const imageurl = ref('')
const prodir = ref([])
const dbSelected = (row) => {
  imageurl.value = ctxImg + row.imageurl
  prodir.value = row.projects.map(item=>{
    return item
  }).filter(item => item!=null)
  console.log(prodir.value)
  dialogTableVisible.value = true
  // console.log(row)
}
onMounted(() => {
  getTechinfo()
  getProtype()
  getProBus()

})
// onBeforeUpdate(()=>{
//   nextTick(()=>{
//     table.value.doLayout()
//   })
// })

let resetForm = ref()
const formInline = reactive({
  techname: '',
  protypeValue:'',
  proBusValue:''
})
const onSubmit = () => {
  http.post('/technician/getTechniciansPage', {
    page: currentPage.value,
    limit: pageSize.value,
    techname: formInline.techname,
    busid: formInline.proBusValue,
    protypeid: formInline.protypeValue
  })
    .then(res => {
      newTotal.value = res.data.data.total
      tableData.value = res.data.data.list
    })
  // console.log(formInline)
}
const onReset = () => {
  resetForm.value.resetFields()
  getTechinfo()
  // console.log('reset!')
}
//添加数据
const onAdd = () => {
  addOrEdit.value = 0
  store.TecdialogVisible = true
  // console.log('onAdd!')
}





</script>

<style lang="scss" scoped>
.step {
  font-size: 12px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  gap: 10px;
}
.proitem{
  border: 1px solid red;
  padding: 5px;
  border-radius:10px ;
  background-color: rgb(241, 186, 83);
}
.image {
  width: 100%;
  display: block;
}
</style>
