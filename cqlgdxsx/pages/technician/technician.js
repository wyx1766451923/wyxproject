var app = getApp()
// pages/technician/technician.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tecData:[],
    apiUrl:app.globalData.httpUrl,
    imageUrl:app.globalData.httpImageUrl
  },
  toTecDetails(evt){
    var jsonStr = JSON.stringify(evt.currentTarget.dataset.value)
    wx.navigateTo({
      url: `/pages/tecDetail/tecDetail?jsonStr=${jsonStr}`
    })

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      method:'post',
      url: that.data.apiUrl+'technician/getTechnicians',
      data:{

      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success (res) {
        console.log("获取项目信息",res.data)
        if(res.data.success){  //说明请求成功，把返回的数据，设置给data
          that.setData({
            tecData:res.data.data
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