<template>
  <el-dialog
    v-model="store.ProdialogVisible"
    title="新增/修改产品"
    width="50%"
    :before-close="handleClose"
  >
    
  <el-form :inline="true" :model="formInline" class="demo-form-inline" ref="resetForm">
    <el-form-item label="商品名" prop="proname">
      <el-input v-model="formInline.proname" placeholder="请输入商品名" clearable />
    </el-form-item>
    <el-form-item label="商品类型" prop="protypeValue">
      <el-select v-model="formInline.protypeValue" class="m-2" placeholder="请选择商品类型" size="large">
        <el-option
        v-for="item in protypeOption"
          :key="item.id"
          :label="item.name"
          :value="item.id"
        />
      </el-select>
    </el-form-item>
    <el-form-item label="所属公司" prop="proBusValue">
      <el-select v-model="formInline.proBusValue" class="m-2" placeholder="请选择商品所属公司" size="large">
        <el-option
          v-for="item in proBusOption"
          :key="item.id"
          :label="item.busname"
          :value="item.id"
        />
      </el-select>
    </el-form-item>
    <el-form-item label="价格" prop="proprice">
      <el-input v-model="formInline.proprice" placeholder="请输入价格" clearable />
    </el-form-item>
    <el-form-item prop="proexplain" label-width="100%" class="textarea" style="min-height: 100px;">
        
        <el-input
            v-model="formInline.proexplain"
            autosizes
            type="textarea"
            placeholder="请输入商品描述" />
    </el-form-item>
    <el-form-item label="图片" label-width="80px" prop="UploadImage">
        <el-upload
            class="avatar-uploader"
            :action="`${ctx}/upload`"
            :headers="{}"
            :data="{
                type:1
            }"
            :show-file-list="false"
            :on-success="uploadSuccess"
            :on-error="uploadError"
            v-model="formInline.UploadImage"
        >
            <img v-if="formInline.UploadImage" :src="imageUrl" class="avatar" />
                <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
            </el-upload>
    </el-form-item>
    <el-form-item prop="prostep" label-width="auto" class="textarea" style="min-height: 100px;">
        
        <el-input
            v-model="formInline.prostep"
            autosizes
            type="textarea"
            placeholder="请输入商品步骤" />
    </el-form-item>
  </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="onCancel">取消</el-button>
        <el-button type="primary" @click="onSubmit">
          提交
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref , onMounted , reactive , nextTick } from 'vue'
import { ElMessageBox , ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import http from '../util/http.js'
import { useShowStore } from '../stores/Show.js'
import { getCtxImg , getCtx } from '../util/common.js'
import { useRouter } from 'vue-router'
const ctxImg = getCtxImg()
const ctx = getCtx()
const props = defineProps(["data","addOrEdit"])
const store = useShowStore()
const resetForm = ref('')
let id = null
const formInline = reactive({
  proname: '',
  proprice:'',
  protypeValue:'',
  proBusValue:'',
  proexplain:'',
  prostep:'',
  UploadImage:''
})
const echoData = () =>{
  console.log(props.addOrEdit)
  if(props.addOrEdit == 0){
    nextTick(()=>{
        resetForm.value.resetFields()
    })    
  }
  else{
    id = props.data.id,
    formInline.proname = props.data.proname,
    formInline.proprice = props.data.proprice,
    formInline.protypeValue = props.data.protypeid,
    formInline.proBusValue = props.data.busid,
    formInline.proexplain = props.data.proexplain,
    formInline.prostep = props.data.prostep,
    formInline.UploadImage = props.data.image.imageurl
    imageUrl.value = ctxImg+formInline.UploadImage
  }
  
}

const handleClose = (done) => {
  ElMessageBox.confirm('确定关闭吗?')
    .then(() => {
      nextTick(()=>{
        resetForm.value.resetFields()
      })
      store.ProdialogVisible = false
      done()
    })
    .catch(() => {
      // catch error
    })
}
const protypeOption = ref([])
const proBusOption = ref([])
// 获取产品类型数据
const getProtype = ()=>{
  http.get('/projecttype/getList')
  .then(res=>{
    protypeOption.value=res.data.data
    // console.log(protypeOption.value)
  })
}
//获取公司数据
const getProBus = ()=>{
  http.get('/business/getList')
  .then(res=>{
    proBusOption.value=res.data.data
    // console.log(proBusOption.value)
  })
}
let newdata
let imgIsChanged = false
const AddProjectInfo = () =>{
    
    if(props.addOrEdit == 0){
      newdata = {}
    }else{
      newdata = JSON.parse(JSON.stringify({...props.data}))
    }
    console.log(newdata)
    newdata.id=id,
    newdata.proname= formInline.proname,
    newdata.proprice=formInline.proprice,
    newdata.protypeid=formInline.protypeValue,
    newdata.busid=formInline.proBusValue,
    newdata.proexplain=formInline.proexplain,
    newdata.prostep=formInline.prostep,
    newdata.imageurl = formInline.UploadImage,
    newdata.imgIsChanged = imgIsChanged
    // console.log(newdata)
    http.post('/project/save',newdata,{
      headers: {
          'Content-Type': 'application/json'
        }
    })
  .then(res=>{
    if(res.data.success){
      // console.log("成功")
    }
  })
}

onMounted(() => {
  getProtype()
  getProBus()
  
})



const imageUrl = ref('')
// 文件上传
// 图片上传成功的函数
const uploadSuccess = (res)=> {
  // console.log(res)
      if (res.success) {
        ElMessage({
          type: 'success',
          message: '上传成功'
        })
        
        formInline.UploadImage = res.data
        imageUrl.value = ctxImg+formInline.UploadImage
        imgIsChanged = true
      } else {
        ElMessage({
          type: 'warning',
          message: res.msg
        })
      }
}
//图片上传失败的回调
const uploadError = ()=> {
      ElMessage({
        type: 'error',
        message:'上传失败'
      })
}
const onCancel = () =>{
    // console.log("取消")
    nextTick(()=>{
        resetForm.value.resetFields()
      })
    store.ProdialogVisible = false
    // console.log(props.data)
    // console.log(props.addOrEdit)
}
const onSubmit = () =>{
    // console.log("提交")
    AddProjectInfo()
    
    nextTick(()=>{
        resetForm.value.resetFields()
    })
    store.ProdialogVisible = false
    location.reload()
    useRouter.go(0)
}
defineExpose({
  echoData
});
</script>

<style lang="scss" scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
.textarea{
    width: 400px;
}

.avatar-uploader .el-upload {
  border: 1px solid var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
  img{
    width: 100px;
    height: 100px;
  }
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  border:1px solid black ;
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  text-align: center;
}
</style>
