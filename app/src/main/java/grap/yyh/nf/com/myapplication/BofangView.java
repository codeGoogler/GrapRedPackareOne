package grap.yyh.nf.com.myapplication.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import grap.yyh.nf.com.myapplication.R;
import grap.yyh.nf.com.myapplication.utils.DensityUtil;

/**
 * Created by Administrator on 2017/6/18 0018.
 */

public class BofangView extends View {

    int COMPONENT_WIDTH;//控件的宽度
    int COMPONENT_HEIGHT;//控件的高度

    boolean initflag = false;//是不是已经初始化图片

    Bitmap[] bmp;//用来寄存图片的数组

    int currPicIndex = 0;//以后播放图片的ID

    private int[] bitmapId;//图片编号ID

    private boolean workFlag = true;//播放图片的线程标识位
    private Paint paint;
    private int currIndex = 0;

    public BofangView(Context father, AttributeSet as)//重写构造函数
    {
        super(father,as);
        paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setColor(ContextCompat.getColor(father,R.color.red));
        //首先，要播放图片，就先要有图片，那就先给各个图片编号吧,这里的图片资源寄存在了res下的drawable文件夹下了
         bitmapId = new int[]{R.drawable.iv_current_out01, R.drawable.iv_current_out03, R.drawable.iv_current_out02};
        //好了，图片的编号当初已经搞定了，接下来该干什么呢？对，应该将资源里的图片塞进Bitmap数组了，那么我们先来确定将要播放的图片的数量，即Bitmap数组的长度
        bmp = new Bitmap[bitmapId.length];//这里不要直接将数值赋给bmp，因为我们可能会不定期地改换图片资源，这样我们就要修改多处代码，而我们这样根据
        //图片的ID来确定图片的数量，以减少不必要的费事，下面开始初始化图片，我们将初始化图片放在一个函数里
        initBitmap();//图片初始化终了
    //图片初始化终了了，接下来我们要做的就是播放图片了，但是播放图片之前，我们有一个问题，就是怎样让图片实现循环播放？这里我们另开一个新的线程来准时变动
    //要播放的图片的ID，以实现图片的循环播放，要实现循环播放图片的功能，我们需要覆写onDraw函数，首先，我们来新开一个线程
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.iv_current_out01);
        setMeasuredDimension(DensityUtil.dip2px(getContext(),211),DensityUtil.dip2px(getContext(),211));
    }


    //初始化图片

    public void initBitmap () {
//获得资源图片
        Resources res = this.getResources();
        for (int i = 0; i < bitmapId.length; i++)
        {
            bmp[i] = BitmapFactory.decodeResource(res, bitmapId[i]);
        }
    }

//覆写onDraw方法
    @Override
    protected void onDraw (Canvas canvas)
    {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        //canvas.drawColor(ContextCompat.getColor(getContext(),R.color.green));
        Bitmap bitmap = bmp[currIndex];
        int cwidth = bitmap.getWidth();
        int cheight= bitmap.getHeight();
        int left = DensityUtil.dip2px(getContext(),211) / 2 - cwidth/2;
        int top = DensityUtil.dip2px(getContext(),211) / 2 - cheight/2;
        canvas.drawBitmap(bitmap, left, top, paint);//绘制图片
    }
    public void startAnation(){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleWithFixedDelay(new runner(), 0, 1, TimeUnit.SECONDS);
        //或者用scheduler.scheduleAtFixedRate(new runner(),0,1, TimeUnit.SECONDS);

        //接着我们要实现Runnable方法，也就是准时变动现在播放图片的ID
    }
    public class runner implements Runnable
    {
        public void run() {
            // TODO Auto-generated method stub
            currIndex = (currIndex+1)%bitmapId.length;
            BofangView.this.postInvalidate();//刷新屏幕
        }
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (!initflag)//检查是偶已经获得控件的宽和高，如果没有，那么就获得控件的宽和高
        {
            COMPONENT_WIDTH = this.getWidth();
            COMPONENT_HEIGHT = this.getHeight();
            initflag = true;
        }
    }
}

