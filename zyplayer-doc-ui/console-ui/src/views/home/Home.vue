<template>
    <div style="padding: 10px;">
        <div style="max-width: 800px;margin: 20px auto;">
            <div style="padding: 20px;">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>所有产品</span>
                    </div>
                    <div class="product-list">
                        <div class="item" v-on:click="jumpToDocPage('doc-wiki')">
                            <div class="logo-text text3">文档</div>
                            <div>文档管理</div>
                        </div>
                    </div>
                </el-card>
            </div>
        </div>
    </div>
</template>

<script>
export default {
	data() {
		return {
		};
	},
	methods: {
		jumpToDocPage(val) {
			window.open(val);
		},
	}
}
</script>
<style>
    .product-list{text-align: left;}
    .product-list .item{
        text-align: center;display: inline-block;padding: 10px;border-radius: 5px;cursor: pointer;
        width: 110px; height: 100px;color: #666;
    }
    .product-list .item:hover{background: #ddd;}
    .product-list .item.disabled{background: #fff;cursor: auto;}
    .product-list .item.disabled .logo-text{background: #909399;}
    .product-list .item .logo-text{
        width: 80px; height: 80px;line-height: 80px;text-align: center; color: #fff;
        margin: 0 auto;background: #67C23A; border-radius: 50%;overflow: hidden;
        font-weight: bold;
    }
    .product-list .item .logo-text.text1{background: #67C23A;}
    .product-list .item .logo-text.text2{background: #E6A23C;}
    .product-list .item .logo-text.text3{background: #F56C6C;}
    .product-list .item .logo-text.text4{background: #387BCD;}
    .product-list .item .logo-text.text5{background: #298b8e;}
    .product-list .item .logo-img{width: 80px; height: 80px;margin: 0 auto;}
    .product-list .item .logo-img img{width: 65px; height: 65px; margin: 7px;}
</style>

