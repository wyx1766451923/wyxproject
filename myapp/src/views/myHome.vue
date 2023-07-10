
<template>
    <div class="common-layout">
        <el-container>
            <el-header>
                <div class="title">
                    美容小程序后台管理系统
                </div>
                <div class="user">
                    <el-dropdown>
                        
                        <span class="el-dropdown-link">
                            <div class="avatar">
                                <el-avatar :icon="UserFilled" />
                            </div>
                            <div class="info">
                                {{username}}
                                <el-icon class="el-icon--right">
                                    <arrow-down />
                                </el-icon>
                            </div>
                        </span>
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item>基本资料</el-dropdown-item>
                                <el-dropdown-item>安全设置</el-dropdown-item>
                                <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
                                
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </div>

            </el-header>
            <el-container>
                <el-aside width="200px">
                    <SideMenu />
                </el-aside>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>

    <!-- 退出登录对话框 -->
    <el-dialog
    v-model="dialogVisible"
    title="提示"
    width="30%"
    :before-close="handleClose"
  >
    <span>你确定要退出登录吗？</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmLogout">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ArrowDown } from '@element-plus/icons-vue'
import SideMenu from "../components/SideMenu.vue";
import { UserFilled } from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessageBox } from 'element-plus'
import { useRouter } from 'vue-router'
const dialogVisible = ref(false)
const router = useRouter()
const handleClose = (done) => {
  ElMessageBox.confirm('确定取消？')
    .then(() => {

      done()
    })
    .catch(() => {
      // catch error
    })
}
const logout = () =>{
    dialogVisible.value = true
}
const confirmLogout = () =>{
    localStorage.removeItem('loginuser')
    localStorage.removeItem('usertoken')
    dialogVisible.value = false
    router.push('/login')
    
}
const username = JSON.parse(localStorage.getItem('loginuser')).username
</script>

<style lang="scss" scoped>
.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
.el-container {
    height: 100vh;
}

.el-header {
    background-color: #454b51;
    display: flex;
    align-items: center;
    justify-content: space-between;
    .title{
        color: #189F93;
        
    }
    .user{
        
        .el-dropdown {
            color: white;
            span{
                display: flex;
                .info{
                    padding-left: 5px;
                    padding-top: 12px;
                }
            }
        }
    }

}
</style>
