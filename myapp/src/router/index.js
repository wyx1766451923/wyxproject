import { createRouter, createWebHistory } from 'vue-router'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/home',
      
      redirect:()=>{
        return '/home/user'
      }
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/myLogin.vue')
    },
    {
      path: '/home',
      name: 'home',
      component: () => import('../views/myHome.vue'),
      children:[
        {
          path: 'user',
          name: 'user',
          component: () => import('../views/myUser.vue')
        },
        {
          path: 'project',
          name: 'project',
          component: () => import('../views/myProject.vue')
        },
        {
          path: 'technician',
          name: 'technician',
          component: () => import('../views/myTechnician.vue')
        }
      ]
    }
    
  ]
})
//登录检验
router.beforeEach((to,from,next)=> {
  if(to.path === '/login'){
    next()
  }else{
    if(localStorage.getItem('usertoken') == null || localStorage.getItem('usertoken') == ''){
      next({
        path:'/login'
      })
    }else{
      next()
    }
  }
}) 

export default router
