package com.demo.renlei.immersivestatebardemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * rl 测试一个简单的自定义View
 */
public class MyTestView extends View {

    private Paint mPaint; //定义一个画笔
    private int mColor;

    /**
     * c自定义View有四个构造函数
     * 如果View在java代码中创建，调用第一个
     * 如果在.xml文件中声名，调用第二个（自定义属性是从AttributeSet参数传入)
     *
     * @param context
     */
    public MyTestView(Context context) {
        super(context);
        init();
    }

    public MyTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    //不会主动调用，一般在第二个构造函数中调用，如View中有style属性时
    public MyTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //加载自定义属性集合
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTestView);
        //解析集合中的属性，将该属性的ID传入画笔中,第二个参数是设置默认颜色，即无属性时的颜色
        mColor = typedArray.getColor(R.styleable.MyTestView_circle_color, Color.WHITE);
        //解析后释放资源
        typedArray.recycle();
        init();
    }

    //API24之后调用，一般在第二个构造函数中调用，如View中有style属性时
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        mPaint = new Paint(); //初始化画笔对象
        mPaint.setColor(mColor); //设置画笔颜色
        mPaint.setStrokeWidth(5f); //设置画笔宽度
        mPaint.setStyle(Paint.Style.FILL); //设置画笔风格为填充
    }

    /**
     * 测量方法
     *
     * @param widthMeasureSpec  包含宽，（wrap_content,match_content,固定）
     * @param heightMeasureSpec
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(widthMeasureSpec);
        int heigh = getMySize(heightMeasureSpec);
        if (width < heigh) {
            heigh = width;
        } else {
            width = heigh;
        }
        setMeasuredDimension(width, heigh);
    }

    /**
     * 用于测量需要的大小
     *
     * @param measureSpec
     */
    private int getMySize(int measureSpec) {
        int mySize = 0, defaultSize = 100; //设置一个默认值，没有输入时，默认100
        int mode = MeasureSpec.getMode(measureSpec);//获取测量模式
        int size = MeasureSpec.getSize(measureSpec); //获取控件大小
        if (mode == MeasureSpec.UNSPECIFIED) {  //表示没有指定大小，使用默认值
            mySize = defaultSize;
        }
        if (mode == MeasureSpec.AT_MOST) { //测量模式为取父类给的最大值
            mySize = size;
        }
        if (mode == MeasureSpec.EXACTLY) { //测量模式为固定大小
            mySize = size;
        }
        return mySize;
    }

    //复写onDraw()开始绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 获取传入的padding值
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();

        //获取控件高度宽度,需要考虑四个方向的padding值
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingBottom - paddingTop;
        //设置圆的半径 = 宽，高最小值的2分之1
        int r = Math.min(width, height) / 2;
        //开始画圆
        //圆心 = 控件的中央；半径 =宽，高最小值的1/2
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, r, mPaint);
    }
}
