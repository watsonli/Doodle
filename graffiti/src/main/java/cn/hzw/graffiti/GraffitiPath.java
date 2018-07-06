package cn.hzw.graffiti;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import cn.hzw.graffiti.core.IGraffiti;

/**
 * 涂鸦轨迹
 * Created by huangziwei on 2017/3/16.
 */

public class GraffitiPath extends GraffitiItemBase {
    private Path mPath; // 画笔的路径

    private PointF mSxy = new PointF(); // 映射后的起始坐标，（手指点击）
    private PointF mDxy = new PointF(); // 映射后的终止坐标，（手指抬起）

    private Paint mPaint = new Paint();

    private CopyLocation mCopyLocation;

    public GraffitiPath(IGraffiti graffiti) {
        super(graffiti);
    }

    public GraffitiPath(IGraffiti graffiti, GraffitiPaintAttrs attrs) {
        super(graffiti, attrs);
    }

    public void reset(IGraffiti graffiti, float sx, float sy, float dx, float dy) {
//        setGraffiti(graffiti);
        setPen(graffiti.getPen());
        setShape(graffiti.getShape());
        setSize(graffiti.getSize());
        setColor(graffiti.getColor().copy());

        mSxy.set(sx, sy);
        mDxy.set(dx, dy);
        if (graffiti instanceof GraffitiView) {
            mCopyLocation = ((GraffitiView) graffiti).getCopyLocation().copy();
        } else {
            mCopyLocation = null;
        }
    }

    public void reset(IGraffiti graffiti, Path p) {
//        setGraffiti(graffiti);
        setPen(graffiti.getPen());
        setShape(graffiti.getShape());
        setSize(graffiti.getSize());
        setColor(graffiti.getColor().copy());

        this.mPath = p;
        if (graffiti instanceof GraffitiView) {
            mCopyLocation = ((GraffitiView) graffiti).getCopyLocation().copy();
        } else {
            mCopyLocation = null;
        }
    }

    public CopyLocation getCopyLocation() {
        return mCopyLocation;
    }

    public Path getPath() {
        return mPath;
    }

    public PointF getDxy() {
        return mDxy;
    }

    public PointF getSxy() {
        return mSxy;
    }

    public static GraffitiPath toShape(IGraffiti graffiti, float sx, float sy, float dx, float dy) {
        GraffitiPath path = new GraffitiPath(graffiti);
        path.setPen(graffiti.getPen());
        path.setShape(graffiti.getShape());
        path.setSize(graffiti.getSize());
        path.setColor(graffiti.getColor().copy());

        path.mSxy.set(sx, sy);
        path.mDxy.set(dx, dy);
        if (graffiti instanceof GraffitiView) {
            path.mCopyLocation = ((GraffitiView) graffiti).getCopyLocation().copy();
        } else {
            path.mCopyLocation = null;
        }
        return path;
    }

    public static GraffitiPath toPath(IGraffiti graffiti, Path p) {
        GraffitiPath path = new GraffitiPath(graffiti);
        path.setPen(graffiti.getPen());
        path.setShape(graffiti.getShape());
        path.setSize(graffiti.getSize());
        path.setColor(graffiti.getColor().copy());

        path.mPath = p;
        if (graffiti instanceof GraffitiView) {
            path.mCopyLocation = ((GraffitiView) graffiti).getCopyLocation().copy();
        } else {
            path.mCopyLocation = null;
        }
        return path;
    }

    @Override
    protected void doDraw(Canvas canvas) {
        mPaint.setStrokeWidth(getSize());
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);

        getColor().initColor(mPaint,this);

        getShape().draw(canvas,this, mPaint);
    }


}

