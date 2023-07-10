import './assets/main.css'
import './assets/shake.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(ElementPlus)
app.use(createPinia()) 
app.use(router)
// input框抖动
app.directive("shake",(el,binding,vnode,preVnode) => {
    el.addEventListener('click', () => {
        el.style.animation = "shake 0.82s cubic-bezier(.36,.07,.19,.97) both"
        el.addEventListener('animationend',() => {
            el.style.animation =""
        })
    })      
})



app.mount('#app')
