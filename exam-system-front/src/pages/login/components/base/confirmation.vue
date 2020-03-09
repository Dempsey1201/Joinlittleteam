<template>
    <div ref="confirmation" class="confirmation" id="container"
    >
        <div class="title">
            请完成安全认证
            <i class="el-icon-close" @click="close"></i>
        </div>
        <div class="draw">
            <!-- 画布 -->
            <canvas width="310" height="155" ref="canvas" id="canvas"></canvas>
            <!-- 裁剪出俩的拼图 -->
            <canvas width="310" height="155" ref="block" id="block"></canvas>
        </div>
        <div class="slide">
            <div class="btn"
                @mousedown="mousedown"
                 ref="btn"
                 :class="{down:mouseIn,false:problem}"
            >
                <i :class="{'el-icon-right':!problem,'el-icon-close':problem}"></i>
            </div>
            <div class="text" >
                <span v-show="!mouseIn">向右拖动滑块填充拼图</span>
            </div>
            <div class="iden" :class="{false:problem}" ref="iden"></div>
        </div>
    </div>
</template>

<script>
	export default {
		name: "confirmation",
        data(){
		    return{
		    	w_:350,
			    canvas_ctx:"",
			    block_ctx:"",
                x:0,//判断移动后x值是否一致
                y:0,
                w:30,//方块大小
                r:8,//小圆半径
                mouseIn:false,//
                start:0,//鼠标相对于window初始位置
                btnStart:0,//滑块相对于父级初始位置
                problem:false//为true 验证失败
            }
        },
        mounted() {
	        //开始作画
			this.canvas_ctx = document.getElementById("canvas").getContext("2d")
            this.block_ctx = document.getElementById("block").getContext("2d")

            this.restart();

	        this.$refs.btn.style.left = "0px"
            document.addEventListener("mousemove",this.mousemove)
            document.addEventListener("mouseup",this.mouseup)
        },
        methods:{
            imgLoad(){//图片加载
	            let img = document.createElement("img");
	            img.setAttribute("crossOrigin",'Anonymous')
	            img.onload = ()=>{
		            this.canvas_ctx.drawImage(img, 0, 0, this.w_, this.w_/2);
		            this.block_ctx.drawImage(img, 0, 0, this.w_, this.w_/2);
		            console.log(111);
		            let blockWidth = this.w + this.r * 2;//滑块的宽度
			        let _y = this.y - this.r * 2 + 2;// 滑块实际的y坐标
			        let ImageData = this.block_ctx.getImageData(this.x, _y, blockWidth, blockWidth);
		            this.$refs.block.width = blockWidth;
		            this.block_ctx.putImageData(ImageData, 0, _y)
	            };
	            img.setAttribute("src","https://p0.ssl.qhimg.com/dmfd/228_99_75/t01df45b95ad444e5ac.webp?")
            },
            drawBlock(){//画出拼图
	            let PI = Math.PI;
	            function draw(ctx, operation,x,y,w,r) {
		            ctx.beginPath()
		            ctx.moveTo(x, y)
		            ctx.arc(x + w / 2, y - r + 2, r, 0.72 * PI, 2.26 * PI)
		            ctx.lineTo(x + w, y)
		            ctx.arc(x + w + r - 2, y + w / 2, r, 1.21 * PI, 2.78 * PI)
		            ctx.lineTo(x + w, y + w)
		            ctx.lineTo(x, y + w)
		            ctx.arc(x + r - 2, y + w / 2, r + 0.4, 2.76 * PI, 1.24 * PI, true)
		            ctx.lineTo(x, y)
		            ctx.lineWidth = 2
		            ctx.fillStyle = 'rgba(255, 255, 255, 0.7)'
		            ctx.strokeStyle = 'rgba(255, 255, 255, 0.7)'
		            ctx.stroke()
		            ctx[operation]()
		            ctx.globalCompositeOperation = 'destination-over'
	            }
	            draw(this.canvas_ctx,'fill',this.x,this.y,this.w,this.r);
	            draw(this.block_ctx,'clip',this.x,this.y,this.w,this.r)
            },
            resize(){
	            this.x = Math.floor((this.w_/2)+Math.random()*(this.w_/2-16-this.w));
	            this.y = Math.floor(16+Math.random()*(this.w_/2-this.w-16));
	            this.$refs.canvas.setAttribute("width",this.w_+"");
	            this.$refs.canvas.setAttribute("height",(this.w_/2)+"");
	            this.$refs.block.setAttribute("width",this.w_+"");
	            this.$refs.block.setAttribute("height",this.w_/2+"");
            },
	        mousedown(e){
            	this.mouseIn = true;
                this.start = e.pageX;
		        this.btnStart = parseInt(this.$refs.btn.style.left);
		        this.$refs.btn.style.transition="0s";
            },
            mousemove(e){
            	if(this.mouseIn){
            		this.$refs.iden.style.border = "1px solid #67C23A"
            		let x = e.pageX//鼠标当前位置
                    let x_ = x - this.start;//鼠标相对移动位置
                    this.$refs.btn.style.left =this.btnStart+ x_+"px";
                    if(parseInt(this.$refs.btn.style.left)<0){
	                    this.$refs.btn.style.left="0px"
                    }
                    if(parseInt(this.$refs.btn.style.left)>310){
	                    this.$refs.btn.style.left="310px"
                    }
                    this.$refs.block.style.left = this.$refs.btn.style.left;
                    this.$refs.iden.style.width = this.$refs.btn.style.left;
                }
            },
	        mouseup(e){
            	if(this.mouseIn){
		            this.$refs.iden.style.border = "";
		            this.mouseIn = false;
		            let pos = parseInt(this.$refs.block.style.left);
		            if(this.x-5<=pos&&this.x+5>=pos){
			            this.$emit("success",{code:true})
			            this.restart()
		            }else{
			            this.$message.error('请将滑块移动到正确的位置');
			            this.problem = true;//验证失败
			            this.$refs.btn.style.transition="0.3s";
			            setTimeout(()=>{
				            this.restart()
			            },500)
		            }
                }
            },
            restart(){
	            this.$refs.btn.style.left = "0px";
	            this.$refs.iden.style.width = "0px";
	            this.$refs.block.style.left = "0px";
	            //设置canvas的宽高
	            this.resize();
	            //加载图片
	            this.imgLoad();
	            this.drawBlock();
		        this.problem = false;
            },
	        close(){//关闭，没有验证成功
		        this.$emit("success",{code:false})
            }
        }
	}
</script>

<style scoped lang="less">
    .confirmation{
        user-select: none;
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        margin: auto;
        padding: 0 20px;
        width: 350px;
        height: 280px;
        background-color: #fff;
        border-radius: 10px;
        .title{
            height: 45px;
            line-height: 45px;
            font-size: 20px;
            color: #424242;
            .el-icon-close{
                float: right;
                line-height: 45px;
            }
            .el-icon-close:hover{
                color: red;
                cursor: pointer;
            }
        }
        .draw{
            position: relative;
            width: 350px;
            height: 175px;
            canvas{
                position: absolute;
                top: 0;
                left: 0;
            }
            #canvas{
                background-color: #fff;
            }
            #block{
                z-index: 10;
            }
        }

        .slide{
            position: relative;
            margin: 10px 0;
            width: 350px;
            height: 40px;
            color: #424242;
            border-radius: 5px;
            .text{
                width: 100%;
                height: 40px;
                line-height: 40px;
                text-align: center;
                background-color: #fefefe;
                font-size: 18px;
                letter-spacing: 3px;
                border-radius: 5px;
                border:1px solid #ddd;
            }
            .btn{
                position: absolute;
                top: 0;
                left: 0px;
                width: 40px;
                height: 40px;
                line-height: 40px;
                text-align: center;
                font-size: 25px;
                cursor: pointer;
                border:1px solid #ddd;
                border-radius: 5px;
                background-color: #fff;
            }
            .btn:hover{
                background-color: #409eff;
                color: #fff;
            }
            .btn.down{
                background-color: #409eff;
                color: #fff;
            }
            .iden{
                position: absolute;
                top: 0;
                left: 0;
                height: 40px;
                background-color: rgb(225, 243, 216);
                border-bottom-left-radius: 5px;
                border-top-left-radius: 5px;
            }
            .btn.false{
                background-color: #F56C6C;
                border: 1px solid #F56C6C;
                color: white;
            }
            .iden.false{
                border:1px solid #F56C6C !important;
                background-color: rgb(253, 226, 226);
            }
        }
    }
</style>