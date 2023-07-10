<template>
  <!-- 表单 -->
  <el-form :inline="true" :model="formInline" class="demo-form-inline" ref="resetForm">
    <el-form-item label="昵称" prop="nickname">
      <el-input v-model="formInline.nickname" placeholder="请输入昵称" clearable />
    </el-form-item>
    <el-form-item label="用户名" prop="username">
      <el-input v-model="formInline.username" placeholder="请输入用户名" clearable />
    </el-form-item>
    <el-form-item label="手机号" prop="phone">
      <el-input v-model="formInline.phone" placeholder="请输入手机号" clearable />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onReset">重置</el-button>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">搜索</el-button>
    </el-form-item>
  </el-form>
  <!-- 表格 -->
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="id" label="id" width="120" />
    <el-table-column prop="nickname" label="昵称" width="180" />
    <el-table-column prop="username" label="用户名" width="180" />
    <el-table-column prop="phone" label="手机号" width="180" />
  </el-table>
  <el-pagination 
  small 
  background 
  v-model:current-page="currentPage" 
  v-model:page-size="pageSize" 
  :page-size="8"
  :page-sizes="[8, 15, 20]" 
  layout="prev, pager, next,sizes" 
  :total=newTotal
  class="mt-4"
  @current-change="handleCurrentChange" 
  @size-change="handlePagesizeChange" />

</template>

<script setup>
import { ref , onMounted ,reactive } from 'vue';
import http from '../util/http.js'
const currentPage = ref(1)
const pageSize = ref(8)

const handleCurrentChange = (index) => {
  currentPage.value = index
  http.post('/user/userListPage',{
    page:currentPage.value,
    limit:pageSize.value
  })
  .then(res=>{
    newTotal.value = res.data.data.total
    tableData.value=res.data.data.list
  })
}
const handlePagesizeChange = (index) => {
  pageSize.value = index
  http.post('/user/userListPage',{
    page:currentPage.value,
    limit:pageSize.value
  })
  .then(res=>{
    newTotal.value = res.data.data.total
    tableData.value=res.data.data.list
  })
}
let tableData = ref([])
let newTotal = ref(1) 
onMounted(() => {
  http.post('/user/userListPage',{
    page:currentPage.value,
    limit:pageSize.value
  })
  .then(res=>{
    console.log(res)
    newTotal.value = res.data.data.total
    console.log(newTotal)
    tableData.value=res.data.data.list
    console.log(tableData)
  })
})

let resetForm = ref()
const formInline = reactive({
  nickname: '',
  username: '',
  phone: '',
})

const onSubmit = () => {
  http.post('/user/userListPage',{
    page:currentPage.value,
    limit:pageSize.value,
    nickname:formInline.nickname,
    username:formInline.username,
    phone:formInline.phone
  })
  .then(res=>{
    console.log(res)
    newTotal.value = res.data.data.total
    console.log(newTotal)
    tableData.value=res.data.data.list
    console.log(tableData)
  })
}
const onReset = () => {
  resetForm.value.resetFields()
  location. reload()
  // console.log('reset!')
}

</script>

<style lang="scss" scoped></style>
