var app = getApp()
// pages/techDetail/techDetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tecInfo: {
      projects: []
    },
    apiUrl: app.globalData.httpUrl,       //后端接口地址
    imageUrl: app.globalData.httpImageUrl //图片资源地址

  },
  toOrder(){

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    //console.log(options.jsonStr)
    
    var info = JSON.parse(options.jsonStr)
    this.setData({
      tecInfo:info
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})