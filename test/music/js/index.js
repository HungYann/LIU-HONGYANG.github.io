var app = new Vue({
    el:"#app",
    data:{
       city:"",
       arr:[],
       url:"",
       imgUrl:"",
       name:"",
       lyric:""
    },
    methods:{
        getjoke:function(){
            var that = this;
            axios.get("https://autumnfish.cn/search?keywords="+this.city).then(function(response){
                that.arr = response.data.result.songs;
            }).catch(function(error){
                console.log(error);
            });
        },
        fun:function(i,a,b){
            this.name = a+ "-" +b;
            var that = this;
            
            axios.get("https://autumnfish.cn/song/url?id="+i).then(function(response){
                that.url = response.data.data[0].url;
            }).catch(function(error){
                console.log(error);
            })
            axios.get("https://autumnfish.cn/song/detail?ids="+i).then(function(response){
                that.imgUrl = response.data.songs[0].al.picUrl;
                var box = document.getElementById("box");
                box.style.backgroundImage = "url("+that.imgUrl+")";
            }).catch(function(error){
                console.log(error);
            });
            axios.get("https://autumnfish.cn/lyric?id="+i).then(function(response){
                that.lyric = response.data.lrc.lyric.split("\n");
            }).catch(function(error){
                console.log(error);
            });
        }
    }
});