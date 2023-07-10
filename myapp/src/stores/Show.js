import { defineStore } from 'pinia'

export const useShowStore = defineStore('show', {
  state:()=>{
    return {
      ProdialogVisible:false,
      TecdialogVisible:false
    }
  },
  // getters:{
  //   changedialogVisible(state) {
  //     const showDialog = !state.dialogVisible
  //     return showDialog
  //   }
  // }
})
