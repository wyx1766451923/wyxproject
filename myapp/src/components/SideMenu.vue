<template>
    <el-col>
        <el-menu 
            active-text-color="#ffd04b" 
            background-color="#545c64" 
            class="el-menu-vertical-demo" 
            default-active="2"
            text-color="#fff" 
            @open="handleOpen" 
            @close="handleClose"
            :router="true"
        >
            <el-sub-menu index="1">
                <template #title>
                    <el-icon>
                        <location />
                    </el-icon>
                    <span>系统管理</span>
                </template>
                    <el-menu-item index="/home/user">用户管理</el-menu-item>
                    <el-menu-item index="/">系统配置管理</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="2">
                <template #title>
                    <el-icon>
                        <location />
                    </el-icon>
                    <span>业务管理</span>
                </template>
                    <el-menu-item index="/home/project">产品管理</el-menu-item>
                    <el-menu-item index="/home/technician">技师管理</el-menu-item>
            </el-sub-menu>
        </el-menu>
    </el-col>
</template>

<script setup>
import {

  Location

} from '@element-plus/icons-vue'
const handleOpen = () => {
  console.log(1)
}
const handleClose = () => {
  console.log(2)
}
</script>

<style lang="scss" scoped>
.el-col{
    height: 100%;
    .el-menu{
        height: 100%;
    }
}
</style>
