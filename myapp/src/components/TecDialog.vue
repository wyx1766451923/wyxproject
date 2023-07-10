<template>
  <el-dialog
    v-model="store.TecdialogVisible"
    title="新增/修改技师"
    width="50%"
    :before-close="handleClose"
  >
  <el-form :inline="true" :model="formInline" class="demo-form-inline" ref="resetForm">
    <el-form-item label="技师姓名" prop="techname">
      <el-input v-model="formInline.techname" placeholder="请输入技师姓名" clearable />
    </el-form-item>
    <el-form-item label="所属公司" prop="proBusValue">
      <el-select v-model="formInline.proBusValue" class="m-2" placeholder="请选择技师所属公司" size="large">
        <el-option
          v-for="item in proBusOption"
          :key="item.id"
          :label="item.busname"
          :value="item.id"
        />
      </el-select>
    </el-form-item>
    <el-form-item prop="techexplain" label-width="100%" class="textarea" style="min-height: 100px;">
        
        <el-input
            v-model="formInline.techexplain"
            autosizes
            type="textarea"
            placeholder="请输入技师描述" />
    </el-form-item>
    <el-form-item label="图片" label-width="80px" prop="UploadImage">
        <el-upload
            class="avatar-uploader"
            :action="`${ctx}/upload`"
            :headers="{}"
            :data="{
                type:2
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
    <br />
    <el-form-item label="项目列表" prop="checkList">
      <el-checkbox-group v-model="checkList">
        <el-checkbox
                  v-for="item in checkboxList"
                  :label="item.id"
                  :key="item.id"
                >{{item.proname}}</el-checkbox>
      </el-checkbox-group>
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
  techname:'',
  proBusValue:'',
  techexplain:'',
  UploadImage:''
})
const echoData = () =>{
  // console.log(props.addOrEdit)
  if(props.addOrEdit == 0){
    console.log("我运行了")
    nextTick(()=>{
        resetForm.value.resetFields()
    })

  }
  else{
    getProListIsOwn()
    id = props.data.id,
    formInline.techname = props.data.techname
    formInline.proBusValue = props.data.busid
    formInline.techexplain = props.data.techexplain
    formInline.UploadImage = props.data.imageurl
    imageUrl.value = ctxImg+formInline.UploadImage
  }
  
}

const handleClose = (done) => {
  ElMessageBox.confirm('确定关闭吗?')
    .then(() => {
      nextTick(()=>{
        resetForm.value.resetFields()
        checkList.value = null
      })
      store.TecdialogVisible = false
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
const checkList = ref([])
const checkboxList = ref([])
//获取美容项目列表项目,新增时
// ,{tecid}
const getProList = () =>{
  http.get('/project/List')
  .then(res=>{
    console.log(res.data)
    if(res.data !=null && res.data.length>0){
      
      checkboxList.value = res.data
      
    }
    
  })
}
//获取美容项目列表项目,修改时
const getProListIsOwn = () =>{
  http.get('/project/List',{
    params:{
      tecid:props.data.id
    }
    
  })
  .then(res=>{
    // console.log(res.data)
    if(res.data !=null && res.data.length>0){
      
      checkboxList.value = res.data
      checkList.value = res.data.map(item=>{
        if(item.isOwn == 1)
           return item.id
      }).filter(item=>item!=null)
    }
    console.log(checkList.value)
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
    newdata.id = id,
    newdata.techname= formInline.techname,
    newdata.proids = checkList.value,
    newdata.busid=formInline.proBusValue,
    newdata.techexplain=formInline.techexplain,
    newdata.imageurl = formInline.UploadImage,
    newdata.imgIsChanged = imgIsChanged
    // console.log(newdata)
    http.post('/technician/save',newdata,{
      headers: {
          'Content-Type': 'application/json'
        }
    })
  .then(res=>{
    if(res.data.success){
      console.log("成功")
    }
  })
}

onMounted(() => {
  getProtype()
  getProBus()
  getProList()

  
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
    getProList()
    nextTick(()=>{
        resetForm.value.resetFields()
        checkList.value = null
      })
    store.TecdialogVisible = false
    // console.log(props.data)
    // console.log(props.addOrEdit)
}
const onSubmit = () =>{
    console.log("提交")
    AddProjectInfo()
    
    nextTick(()=>{
        resetForm.value.resetFields()
        checkList.value = null
    })
    store.TecdialogVisible = false
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
