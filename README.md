# Depthview_master
k线深度图

使用说明：
-----------------------------------------------------------------------------------------------------------------
                          属性                             |                  属性设置 
-----------------------------------------------------------------------------------------------------------------
            depthview_buy_line_color                       |               买入颜色
-----------------------------------------------------------------------------------------------------------------
            depthview_buy_path_color                       |            买入区域内部颜色
-----------------------------------------------------------------------------------------------------------------
            depthview_sell_line_color                      |                卖出颜色
-----------------------------------------------------------------------------------------------------------------
            depthview_sell_path_color                      |            卖出区域内部颜色
 ----------------------------------------------------------------------------------------------------------------
            depthview_line_width                           |                线条宽度
-----------------------------------------------------------------------------------------------------------------
            depthview_text_color                           |                文字颜色
-----------------------------------------------------------------------------------------------------------------
            depthview_background_color                     |                背景颜色
-----------------------------------------------------------------------------------------------------------------    
            depthview_selector_background_color            |            选中后文字背景颜色
-----------------------------------------------------------------------------------------------------------------
            depthview_line_count                           |            右侧价格行数
-----------------------------------------------------------------------------------------------------------------
             depthview_text_size                          |             文字大小
-----------------------------------------------------------------------------------------------------------------
            depthview_circle_radius                       |               圆圈半径
-----------------------------------------------------------------------------------------------------------------
            depthview_dot_radius                          |              点的半径     
-----------------------------------------------------------------------------------------------------------------
            depthview_trust_price                         |               委托价
-----------------------------------------------------------------------------------------------------------------
            depthview_trust_quantity                      |               委托量
-----------------------------------------------------------------------------------------------------------------
1、Add it in your root build.gradle at the end of repositories:   
          allprojects {
            repositories {
              ...
              maven { url 'https://jitpack.io' }
            }
          }


2、Add the dependency
          dependencies {
            implementation 'com.github.maweijie2000:Depthview_master:1.1.2'
          }

3、在xml中配置

        <com.mwj.depthview.depthview_lib.DepthMapView
              android:id="@+id/dep"
              android:layout_width="match_parent"
              android:layout_height="200dp"
              app:background_color="@color/depth_background"
              app:buy_line_color="@color/depth_buy_line"
              app:buy_path_color="@color/depth_buy_path"
              app:circle_radius="4dp"
              app:dot_radius="3dp"
              app:line_count="4"
              app:line_width="1.5dp"
              app:sell_line_color="@color/depth_sell_line"
              app:sell_path_color="@color/depth_sell_path"
              app:text_color="@color/depth_text_color"
              app:text_size="10sp"
              app:trust_price="成交价"
              app:trust_quantity="成交量" />
        
 4、在代码中获取控件
 
            @BindView(R2.id.depthview)
            com.mwj.depthview.depthview_lib.DepthMapView depthview;
 
 5、控件赋值  
 
        private void newDepth(String jsonData) {
              final List<DepthDataBean> listDepthBuy = new ArrayList<>();
              final List<DepthDataBean> listDepthSell = new ArrayList<>();

              DepthListBean DepthListBean = JSON.parseObject(jsonData, DepthListBean.class);
              if (DepthListBean == null) return;

              final List<String[]> bids = DepthListBean.getBids();
              final List<String[]> asks = DepthListBean.getAsks();

              if (bids != null) {
                  for (int i = 0; i < bids.size(); i++) {
                      DepthDataBean obj = new DepthDataBean();
                      obj.setPrice(Float.parseFloat(bids.get(i)[0]));
                      obj.setVolume(Float.parseFloat(bids.get(i)[1]));
                      listDepthBuy.add(obj);
                  }
              }
              if (asks != null) {
                  for (int i = 0; i < asks.size(); i++) {
                      DepthDataBean obj = new DepthDataBean();
                      obj.setPrice(Float.parseFloat(asks.get(i)[0]));
                      obj.setVolume(Float.parseFloat(asks.get(i)[1]));
                      listDepthSell.add(obj);
                  }
              }


              getMyActivity().runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      depthview.setData(listDepthBuy, listDepthSell);
                  }
              });

    }
 
 
 
   数据返回格式：
                 {
                    "bids":[
                        [
                            "3014",
                            "0.13"
                        ],
                        ........
                    ],
                    "asks":[
                        [
                            "3996.24",
                            "0.001242"
                        ],
                        .........
                    ]
                }
 
 
