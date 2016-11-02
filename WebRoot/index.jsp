<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
	<head>
	    <title>谢永年卫生所</title>
	    <sb:head/>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	
	<style>
		body {
			margin : 5px
		}
		a {
			color:black;
		}
		a:link{
			text-decoration:none;
		}
	</style>
	
	<body class="well">
		<div class="row-fluid">
			<div class="span12">
				<div class="page-header">
		            <h3 class="text-center">护理产品目录</h3>
		            <div id="buy" class="text-right" style="text-decoration:underline">购买方法</div>
		        </div>
	            <div class="media well" style="display:none" id="tip">
			      <div class="pull-left">
			        <img class="media-object" width="100px" src="img/erweima.jpg">
			      </div>
			      <div class="media-body">
			        <h4 class="media-heading">购买方法</h4>
			        	<ol>
			        		<li>长按左方二维码</li>
			        		<li>点击"识别图中二维码"</li>
			        		<li>加工作人员微信咨询购买</li>
			        	</ol>
			      </div>
			    </div>
		        
				<h4 class="text-center"><i class="icon-heart"></i> 保湿乳霜</h4>
		        <ul class="thumbnails">
			      <li class="span12" style="margin-left:0">
			      	<a href="http://mp.weixin.qq.com/s?__biz=MjM5NjI3MTExNg==&mid=2451146043&idx=1&sn=13af7d61e152dc55ed17ee7b59d405ed">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/youze.jpg">
				          <h4>优泽倍润修护霜 <small>¥98</small></h4>
				          <p>纯天然植物提取物，不含激素和人工香料<br>独有的抗炎止痒和保湿双重功效</p>
				          <hr>
				          <p>→进入了解更多</p>
				        </div>
				    </a>
			      </li>
			      <li class="span12" style="margin-left:0">
			      	<a href="">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/sitafulotion.jpg">
				          <h4>丝塔芙保湿润肤乳 <small>¥78</small></h4>
				          <p>滋润肌肤，保持水润，温和无刺激<br>适用于各种皮肤类型，不含香精</p>
				          <hr>
				          <p>暂未更新详情，敬请期待</p>
				        </div>
				    </a>
			      </li>
			      <li class="span12" style="margin-left:0">
			      	<a href="http://mp.weixin.qq.com/s?__biz=MjM5NjI3MTExNg==&mid=2451146166&idx=1&sn=682a138f98d8a729a54b80da55d9ab25">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/anminzibaoshifuliao.jpg">
				          <h4>安敏滋保湿敷料 <small>¥55</small></h4>
				          <p>有效保湿 防止干燥 修复皮肤<br>用于皮肤粘膜创面覆盖、润滑、保湿、防干裂等</p>
				          <hr>
				          <p>→进入了解更多</p>
				        </div>
				    </a>
			      </li>
			      <li class="span12" style="margin-left:0">
			      	<a href="">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/heidou.jpg">
				          <h4>黑豆馏油软膏 <small>¥30</small></h4>
				          <p>消炎 收敛 止痒<br>保湿 护肤 促角质再生</p>
				          <hr>
				          <p>暂未更新详情，敬请期待</p>
				        </div>
				    </a>
			      </li>
			      <li class="span12" style="margin-left:0">
			      	<a href="http://mp.weixin.qq.com/s?__biz=MjM5NjI3MTExNg==&mid=2451146122&idx=1&sn=38b8978eb5d9974dca70660e8a9125fb">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/lingruiluhuijiao.jpg">
				          <h4>羚锐芦荟胶 <small>¥50</small></h4>
				          <p>补水 抗皱纹 淡化痘印<br>滋养受损皮肤 延缓皮肤衰老 增强皮肤弹性</p>
				          <hr>
				          <p>→进入了解更多</p>
				        </div>
				    </a>
			      </li>
			    </ul>
				<h4 class="text-center"><i class="icon-heart"></i> 洗面奶</h4>
		        <ul class="thumbnails">
			      <li class="span12" style="margin-left:0">
			      	<a href="">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/sitafucleanser.jpg">
				          <h4>丝塔芙洁面乳 <small>¥58</small></h4>
				          <p>清洁同时保持肌肤水润，柔嫩爽滑<br>温和无刺激，适用于各种肌肤类型</p>
				          <hr>
				          <p>暂未更新详情，敬请期待</p>
				        </div>
			      	</a>
			      </li>
			      <li class="span12" style="margin-left:0">
			      	<a href="http://mp.weixin.qq.com/s?__biz=MjM5NjI3MTExNg==&mid=2451146092&idx=1&sn=c4b7c5cc066917749f4bda2674f483da">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/anminzipaomo.jpg">
				          <h4>安敏滋氨基酸洁面泡沫 <small>¥50</small></h4>
				          <p>泡沫细腻丰富 温和不刺激<br>抗炎抑菌 改善痘痘<br>调节分泌 平衡油脂</p>
				          <hr>
				          <p>→进入了解更多</p>
				        </div>
			      	</a>
			      </li>
			      <li class="span12" style="margin-left:0">
			      	<a href="http://mp.weixin.qq.com/s?__biz=MjM5NjI3MTExNg==&mid=2451146050&idx=1&sn=0c363dcd46deaf61e5d9afc4c095dd79">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/fumeiling.jpg">
				          <h4>肤美灵洗面奶 <small>¥14</small></h4>
				          <p>抑螨，抑制痘痘，润泽肌肤角质层<br>洗后不干燥，不紧绷，润滑自然</p>
				          <hr>
				          <p>→进入了解更多</p>
				        </div>
			      	</a>
			      </li>
			    </ul>
				<h4 class="text-center"><i class="icon-heart"></i> 爽肤水</h4>
		        <ul class="thumbnails">
			      <li class="span12" style="margin-left:0">
			      	<a href="http://mp.weixin.qq.com/s?__biz=MjM5NjI3MTExNg==&mid=2451146187&idx=1&sn=6d482e77fe0410b30ad3daa26ca562f5">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/anminzisfs.jpg">
				          <h4>安敏滋保湿润肤水 <small>¥80</small></h4>
				          <p>适合敏感肌肤/干性肌肤<br>水润保湿，抗敏舒缓<br>双分子透明质酸<br>100%纯天然——无酒精/无香精/无色素</p>
				          <hr>
				          <p>→进入了解更多</p>
				        </div>
			      	</a>
			      </li>
		      	</ul>		        
				<h4 class="text-center"><i class="icon-heart"></i> 面膜</h4>
		        <ul class="thumbnails">
			      <li class="span12" style="margin-left:0">
			      	<a href="http://mp.weixin.qq.com/s?__biz=MjM5NjI3MTExNg==&mid=2451146072&idx=1&sn=e08d5555391119b873533e8a3d4840c4">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/aipalan.jpg">
				          <h4>爱帕蓝系列面膜 <small>¥150</small></h4>
				          <p>天然蚕丝织物面膜，源于瑞士生物科技<br>日本进口100%水织工艺蚕丝面膜布<br>高达35mL精华液，保湿、美白、抗衰老<br>9种原液选择</p>
				          <hr>
				          <p>→进入了解更多</p>
				        </div>
			      	</a>
			      </li>		        
			      <li class="span12" style="margin-left:0">
			      	<a href="http://mp.weixin.qq.com/s?__biz=MjM5NjI3MTExNg==&mid=303662508&idx=1&sn=b3ed83544358bcc3477af049dee461e9">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/anminzimianmo.jpg">
				          <h4>安敏滋保湿保湿润肌修复蚕丝面膜 <small>¥95</small></h4>
				          <p>有效保湿，深入肌底，令肌肤水润饱满，柔润细致<br>深层修复，重建屏障功能，增强肌肤抵抗力</p>
				          <hr>
				          <p>→进入了解更多</p>
				        </div>
			      	</a>
			      </li>		        
			      <li class="span12" style="margin-left:0">
			      	<a href="http://mp.weixin.qq.com/s?__biz=MjM5NjI3MTExNg==&mid=2451146160&idx=1&sn=8f19aec9fbd30527c9c4297677919b4b">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/runfukang.jpg">
				          <h4>美卿皮肤修复贴敷料 <small>¥95</small></h4>
				          <p>治疗皮肤过敏，促进皮肤修复<br>适用于轻中度炎症较轻痤疮、痤疮愈后早期色素沉着</p>
				          <hr>
				          <p>→进入了解更多</p>
				        </div>
			      	</a>
			      </li>		        
			    </ul>
			    <h4 class="text-center"><i class="icon-heart"></i> 修复疤痕</h4>
		        <ul class="thumbnails">
			      <li class="span12" style="margin-left:0">
			      	<a href="http://mp.weixin.qq.com/s?__biz=MjM5NjI3MTExNg==&mid=303662493&idx=1&sn=2663750d4c0a2dfbdc1e589f455eeadc&chksm=3106e0fe067169e86b1f4f5b8663c12762a93907133b7199037c8263a7ad3f9742ae842de39d&scene=0#wechat_redirect">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/beishuhen.jpg">
				          <h4>倍舒痕硅凝胶 <small>¥360</small></h4>
				          <p>快速干燥外用硅凝胶<br>有效平复及软化疤痕</p>
				          <hr>
				          <p>→进入了解更多</p>
				        </div>
			      	</a>
			      </li>
		      	</ul>
			    <h4 class="text-center"><i class="icon-heart"></i> 止痒</h4>
		        <ul class="thumbnails">
			      <li class="span12" style="margin-left:0">
			      	<a href="">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/lifuban.jpg">
				          <h4>力肤邦草本润肤止痒洗剂 <small>¥25</small></h4>
				          <p>抑菌、止痒、润肤三合一<br>预防细菌交叉感染，对表皮真菌、大肠杆菌、金黄色葡萄球菌具有抗菌、抑菌作用</p>
				          <hr>
				          <p>暂未更新详情，敬请期待</p>
				        </div>
			      	</a>
			      </li>
			      <li class="span12" style="margin-left:0">
			      	<a href="">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/jiedun.jpg">
				          <h4>洁盾草本肤痒洗剂 <small>¥25</small></h4>
				          <p>抑菌、止痒、润肤三合一<br>抗菌止痒 清爽洁肤</p>
				          <hr>
				          <p>暂未更新详情，敬请期待</p>
				        </div>
			      	</a>
			      </li>
		      	</ul>
			    <h4 class="text-center"><i class="icon-heart"></i> 治疗痤疮</h4>
		        <ul class="thumbnails">
			      <li class="span12" style="margin-left:0">
			      	<a href="">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/adapalin.jpg">
				          <h4>阿达帕林凝胶 <small>¥75</small></h4>
				          <p>本品适用于以粉刺、丘疹和脓疱为主要表现的寻常型痤疮的皮肤治疗<br>亦可用于治疗面部、胸和背部的痤疮</p>
				          <hr>
				          <p>暂未更新详情，敬请期待</p>
				        </div>
			      	</a>
			      </li>
		      	</ul>
			    <h4 class="text-center"><i class="icon-heart"></i> 皮炎湿疹</h4>
		        <ul class="thumbnails">
			      <li class="span12" style="margin-left:0">
			      	<a href="">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/ainingda.jpg">
				          <h4>爱宁达（吡美莫司乳膏） <small>¥175</small></h4>
				          <p>适用于无免疫受损的2 岁及2岁以上轻度至中度异位性皮炎（湿疹）患者</p>
				          <hr>
				          <p>暂未更新详情，敬请期待</p>
				        </div>
			      	</a>
			      </li>
		      	</ul>
			    <h4 class="text-center"><i class="icon-heart"></i> 脚部</h4>
		        <ul class="thumbnails">
			      <li class="span12" style="margin-left:0">
			      	<a href="">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/youzi.jpg">
				          <h4>优姿脚跟龟裂膏 <small>¥60</small></h4>
				          <p>有效防治脚跟龟裂和改善足部皮肤质地</p>
				          <hr>
				          <p>暂未更新详情，敬请期待</p>
				        </div>
			      	</a>
			      </li>
		      	</ul>
			    <h4 class="text-center"><i class="icon-heart"></i> 防晒</h4>
		        <ul class="thumbnails">
			      <li class="span12" style="margin-left:0">
			      	<a href="">
				        <div class="thumbnail">
				          <img width="100%" src="http://appointment-img.stor.sinaapp.com/anminziningjiao.jpg">
				          <h4>安敏滋光子冷凝胶 <small>¥65</small></h4>
				          <p>隔热和导光<br>防止皮肤烫伤，缓解疼痛</p>
				          <hr>
				          <p>暂未更新详情，敬请期待</p>
				        </div>
			      	</a>
			      </li>
		      	</ul>
			</div>
		</div>
	</body>
	
	<script src="js/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#buy").click(function(){
				if ($("#tip").css("display") == "none")
					$("#tip").css("display", "block");
				else
					$("#tip").css("display", "none");
			});
		})
	</script>
	
</html>