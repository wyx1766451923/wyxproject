var app = getApp()
// pages/index/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bannerData:[],
    navData:[],
    proData:[],
    apiUrl:app.globalData.httpUrl,
    imageUrl:app.globalData.httpImageUrl
  },

  /**
   * 生命周期函数--监听页面加载
   */
  showProDetail(evt){
    //  console.log(evt.currentTarget.dataset.value)
    var jsonStr = JSON.stringify(evt.currentTarget.dataset.value)
    // console.log(jsonStr)
    wx.navigateTo({
      url: `/pages/proDetail/proDetail?jsonStr=${jsonStr}`
    })

  },
  onLoad: function (options) {
      var that = this;
      //1.发送请求，获取轮播图信息
      wx.request({
        method:"POST",
        url: that.data.apiUrl+'image/queryImageByType', //仅为示例，并非真实的接口地址
        data: {
          imagetype: 'banner',
          imagestate:1
        },
        header: {
          'content-type': 'application/json' // 默认值
        },
        success (res) {
          console.log(res.data)
          if(res.data.success){  //说明请求成功，把返回的数据，设置给data
            that.setData({
              bannerData:res.data.data
            })
          }else{  //失败  提示   失败原因

          }

        }
      })
       //2.发送请求，获取导航菜单信息
       wx.request({
        method:"POST",
        url: that.data.apiUrl+'image/queryImageByType', //仅为示例，并非真实的接口地址
        data: {
          imagetype: 'nav',
          imagestate:1,
        },
        header: {
          'content-type': 'application/json' // 默认值
        },
        success (res) {
          console.log(res.data)
          if(res.data.success){  //说明请求成功，把返回的数据，设置给data
            that.setData({
              navData:res.data.data
            })
          }else{  //失败  提示   失败原因

          }

        }
      })
      //3.发送请求，获取项目信息
      wx.request({
        method:"POST",
        url: that.data.apiUrl+'project/getProinfos', //仅为示例，并非真实的接口地址
        data:{
          
        },
        header: {
          'content-type': 'application/json' // 默认值
        },
        success (res) {
          // console.log("获取项目信息",res.data)
          if(res.data.success){  //说明请求成功，把返回的数据，设置给data
            that.setData({
              proData:res.data.data
            })
          }else{  //失败  提示   失败原因

          }

        }
      })

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})