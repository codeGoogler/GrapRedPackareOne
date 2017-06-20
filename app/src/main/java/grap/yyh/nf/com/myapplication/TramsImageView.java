package grap.yyh.nf.com.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

import grap.yyh.nf.com.myapplication.R;
import grap.yyh.nf.com.myapplication.utils.DensityUtil;


/**
 * Created by Administrator on 2017/6/15/015.
 */

public class TramsImageView extends ImageView {
    private Paint paint;

    public TramsImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.red));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
      /*  Resources res =  getResources();
        Bitmap img = BitmapFactory.decodeResource(res, R.drawable.iv_red_package);
        Matrix matrix = new Matrix();
        matrix.postRotate(180);  /*//*翻转180度*//**//*

        int width = img.getWidth();
        int height = img.getHeight();
        Bitmap img_a = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true);
       canvas.drawBitmap(img_a,matrix ,paint);*/
       canvas.drawLine(0,0, DensityUtil.getScreeFloatnWidth(getContext()), DensityUtil.getScreenHeight(getContext()),paint);
    }

}
