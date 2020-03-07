<template>
    <canvas width="800" height="800" ref="canvasBg">
        Your Browser Don't Support Canvas, Please Download Chrome ^_^``
    </canvas>
</template>

<script>
	export default {
		name: "background"
		, data() {
			return {
				can_w: 0,//canvas 的高
				can_h: 0,//canvas 的宽
				ball: {//小球具有的属性
					x: 0,//位置
					y: 0,
					vx: 0,//速度
					vy: 0,
					r: 0,//半径
					alpha: 1,//小球初始的透明度
					phase: 0//用phase控制 alpha 的值的变化 phase 是弧度数
				},
				R:4,//设定所有小球半径为2
				balls:[],//放小球的数组
				new_balls:[],//更新小球的数组
				alpha_f: 0.03,
				alpha_phase:0,//连线属性
				link_line_width:0.5,
				dis_limit:100,//距离100连线
				add_mouse_point:true,//鼠标小球
				mouse_in:false,
				mouse_ball:{//鼠标小球
					x: 0,
					y: 0,
					vx: 0,
					vy: 0,
					r: 0,
					type: 'mouse'
				},
                num:80
			}
		},
		props:{
			ball_color: {//小球颜色，加上 alpha 就有了透明度
				type: Object
			},
            bg_color:{
				type:String
            }
        }
		, mounted() {
			this.ctx = this.$refs.canvasBg.getContext('2d')
			this.$refs.canvasBg.style.backgroundColor = this.bg_color;
			this.resize();
			window.addEventListener("resize", () => {
				this.can_w = window.innerWidth;//canvas 的宽
				this.can_h = window.innerHeight;
				this.$refs.canvasBg.setAttribute("width", this.can_w + "px")
				this.$refs.canvasBg.setAttribute("height", this.can_h + "px")
			})
            this.goMovie();
			document.addEventListener("mouseenter",(e)=>{
				this.mouse_in = true;
				this.balls.push(this.mouse_ball)
			})
			document.addEventListener("mouseleave",(e)=>{
				this.mouse_in = true;
				let new_balls = [];
				this.balls.forEach(function(b){
					if (!b.hasOwnProperty("type")) {//没有 type 属性
						new_balls.push(b);
					}
				})
				this.balls = new_balls.slice(0)//过滤掉 mouse_ball
			})
			document.addEventListener("mousemove",(e)=>{
				this.mouse_ball.x = e.pageX;
				this.mouse_ball.y = e.pageY;
			})
		},
        methods:{
			resize(){
				this.can_w = window.innerWidth;//canvas 的宽
				this.can_h = window.innerHeight;
				this.$refs.canvasBg.setAttribute("width", this.can_w + "px")
				this.$refs.canvasBg.setAttribute("height", this.can_h + "px")

            },
			//生成min-max随机数
	        randomNumFrom(min, max) {
		        return Math.random() * (max - min) + min;
	        },
	        getRandomSpeed(pos) {//生成随机速度
		        //根据不同方向返回不同情况的速度
		        let min = -0.5,//向右和向下是负的
			        max = 0.5//向上和向左是正的
		        switch (pos) {//四个方向,返回一个数组，里面是 vx 和 vy,速度不能为 0
			        case 'top':
				        return [this.randomNumFrom(min, max), this.randomNumFrom(0.1, max)]
				        break;
			        case 'left':
				        return [this.randomNumFrom(0.1, max), this.randomNumFrom(min, max)]
				        break;
			        case 'right':
				        return [this.randomNumFrom(min, -0.1), this.randomNumFrom(min, max)]
				        break;
			        case 'bottom':
				        return [this.randomNumFrom(min, max), this.randomNumFrom(min, -0.1)]
				        break;
			        default:
				        break;
			        //返回一个数组，[左右方向速度,上下方向速度]
		        }
	        },
	        randomArreyItem(arr){
	        	//返回 arr 数组中的随机一位
		        return arr[Math.floor(Math.random() * arr.length)];//向下取整，防止溢出
	        },
	        getRandomBall() {
	        	//返回一个数据随机的小球
		        let pos = this.randomArreyItem(['top', 'bottom', 'left', 'right']);//随即方向·
		        //方向为随即方向
		        let x, y;
		        switch (pos) {//根据随即方向确定随机的初始位置
			        case 'left':
				        x = -this.R;
				        y = this.randomNumFrom(0, this.can_h)
				        break;
			        case 'right':
				        x = this.can_w + this.R;
				        y = this.randomNumFrom(0, this.can_h)
				        break;
			        case 'top':
				        x = this.randomNumFrom(0,this.can_w);
				        y = -this.R;
				        break;
			        case 'bottom':
				        x = this.randomNumFrom(0,this.can_w);
				        y = this.R + this.can_h
				        break;
			        default:
				        break;
		        }

		        return {
			        x: x,
			        y: y,
			        vx: this.getRandomSpeed(pos)[0],
			        vy: this.getRandomSpeed(pos)[1],
			        r: this.R,
			        alpha: 1,
			        phase: this.randomNumFrom(0, 10)
		        }
	        },
	        initBalls(num){
	        	//初始化所有的小球
		        for(let i = 0;i<num ;i++){
			        this.balls.push({// push进 ball 数组里
				        x:this.randomNumFrom(0,this.can_w),
				        y:this.randomNumFrom(0,this.can_h),
				        vx:this.getRandomSpeed('top')[0],
				        vy:this.getRandomSpeed('top')[1],
				        r:this.R,
				        alpha:1,
				        phase: this.randomNumFrom(0,10)
			        })
		        }
	        },
	        renderBalls(){
	        	//画出小球
		        this.balls.forEach((b)=>{
			        if(!b.hasOwnProperty('type')){
				        this.ctx.fillStyle = 'rgba('+this.ball_color.r+','+this.ball_color.g+','+this.ball_color.b+','+b.alpha+')';
				        this.ctx.beginPath();
				        this.ctx.arc(b.x,b.y,b.r,0,Math.PI*2,true);
				        this.ctx.closePath();
				        this.ctx.fill();//填充
			        }
		        });
	        },
	        updataBalls(){
	        	//让小球运动起来，并实时更新小球
		        this.balls.forEach((b)=>{
			        b.x += b.vx;
			        b.y += b.vy;

			        if(b.x > -(50) && b.x < (this.can_w+50) && b.y > -(50) && b.y < (this.can_h+50)){
				        //把位置符合条件的小球 push 到 new——balls 里面
				        this.new_balls.push(b)
			        }
			        b.phase += this.alpha_f;//alpha_f = 0.03;
			        b.alpha = Math.abs(Math.cos(b.phase))
		        })
		        this.balls = this.new_balls.slice(0);//把符合条件的小球克隆给 balls
		        this.new_balls = []//每次过后清空 new_balls
	        },
	        addBallIfy(){//保证小球数量
		        if(this.balls.length < this.num){
			        this.balls.push(this.getRandomBall());
		        }
	        },
	        getDisOf(b1,b2){//计算两小球之间距离
		        let x_ = Math.abs(b1.x-b2.x);
		        let y_ = Math.abs(b1.y-b2.y);
		        return Math.sqrt(x_*x_+y_*y_)
	        },
	        renderLines(){//画出两小球之间连线
		        let fraction,alpha;
		        this.balls.forEach((b,index)=>{
			        for (let j = index+1; j < this.balls.length; j++) {
				        fraction = this.getDisOf(b,this.balls[j])/this.dis_limit//两小球之间距离

				        if (fraction < 1) {//距离达到要求开始连线
					        // fraction 越大说明距离越远，那么连线透明度应该越低
					        alpha = 1 - fraction;

					        this.ctx.strokeStyle = `rgba(${this.ball_color.r},${this.ball_color.g},${this.ball_color.b},${alpha})`;
					        this.ctx.lineWidth = this.link_line_width//线宽

					        this.ctx.beginPath();
					        this.ctx.moveTo(b.x,b.y);
					        this.ctx.lineTo(this.balls[j].x,this.balls[j].y);
					        this.ctx.closePath();
					        this.ctx.stroke()//画线
				        }

			        }
		        })
	        },
	        render(){
		        this.ctx.clearRect(0,0,this.can_w,this.can_h)

		        this.renderBalls()//画出所有小球

		        this.renderLines()//画线

		        this.updataBalls();//让小球运动,更新小球数组

		        this.addBallIfy()//保证小球数量

		        window.requestAnimationFrame(this.render)//不停作画

	        },
	        goMovie(){
		        this.resize()
		        this.initBalls(this.num);
		        window.requestAnimationFrame(this.render)//激活 render 方法
	        }
        }
	}
</script>

<style scoped>
    canvas {
        position: absolute;
        top: 0;
        left: 0;
        z-index: -1;
        display: block;
    }
</style>