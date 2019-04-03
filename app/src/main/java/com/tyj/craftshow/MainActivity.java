package com.tyj.craftshow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.tyj.craftshow.http.BaseResponse;
import com.tyj.craftshow.http.RetrofitUtil;
import com.tyj.craftshow.http.RxSchedulers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_button1)
    Button btn1;
    @BindView(R.id.btn_button2)
    Button btn2;
    @BindView(R.id.iv_imgs)
    ImageView imageView;

    @BindView(R.id.craftshow_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.craftshow_linear)
    LinearLayout mLinearLayout;

    private MyAdapter mAdapter;
    int mNum=0;
    //图片数组
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPostRequest("超级管理员");//测试网络请求
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPostRequest("业主项目部负责人");//测试网络请求
            }
        });
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151721118&di=649c9a43aed72fbc4d99ec1a031510c6&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F015c7d574b9f8f6ac72525aee98351.jpg");
//        list.add("http://sowcar.com/t6/694/1554169906x1707632075.jpg");
        list.add("https://github.com/mingli20181102/CraftShow/raw/master/%E9%A3%8E%E6%99%AF.jpg");
        list.add("https://raw.githubusercontent.com/mingli20181102/CraftShow/master/pic.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151847685&di=c7a4b5d08ec43fa629bcb690039a7629&imgtype=0&src=http%3A%2F%2Fattimg.dospy.com%2Fimg%2Fday_080625%2F20080625_2e91a10c444877e88827vri2ZKdGMvQo.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151825129&di=70bf74b87d8a15cb91a2d79f15ed0eaf&imgtype=0&src=http%3A%2F%2Fattimg.dospy.com%2Fimg%2Fday_081016%2F20081016_fee215664d5740e56c13E2YB8giERFEX.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505746504&di=930c4d677a02328a142d6fa85ed14580&imgtype=jpg&er=1&src=http%3A%2F%2Fattimg.dospy.com%2Fimg%2Fday_090113%2F20090113_6ac58b42bea94f0b318e1B6BZb5lPZl5.jpg");
        getData(list);
    }

    /**
     * 获取数据
     */
    private void getData(List<String> ls) {
        //设置图片
        View view;
        for (int i=0;i<ls.size();i++) {
            list.get(i);
            //创建底部指示器(小圆点)
            view = new View(getApplicationContext());
            view.setBackgroundResource(R.drawable.viewpage_dot);
            view.setEnabled(false);
            //设置宽高
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            //设置间隔
            layoutParams.leftMargin = 10;
            //添加到LinearLayout
            mLinearLayout.addView(view, layoutParams);
        }

        mAdapter = new MyAdapter(getApplicationContext(),list);
        mViewPager.setAdapter(mAdapter);
        //注册
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                mLinearLayout.getChildAt(mNum).setEnabled(false);
                mLinearLayout.getChildAt(position).setEnabled(true);
                mNum = position;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //第一次显示小白点
        mLinearLayout.getChildAt(mNum).setEnabled(true);
    }

    /**
     * 适配器
     */
    private class MyAdapter extends PagerAdapter {
        private Context context;
        private List<String> list;
        public MyAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        //大小
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //获取图片view
            ImageView imageView = new ImageView(context);
            imageView.setAdjustViewBounds(true);//是否保持宽高比
            imageView.setMaxWidth(1080);
            imageView.setMaxHeight(1920);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //设置到容器,也就是ViewPager
//            ImageLoader.getInstance().displayImage(list.get(position%list.size()),imageView);
            Glide.with(context).load(list.get(position%list.size())).thumbnail(0.5f).into(imageView);
            container.addView(imageView);
            //返回控件
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //从容器中删除
            container.removeView((View)object);
        }
    }

    @SuppressLint("CheckResult")
    private void setPostRequest(String v) {
        Map<String,Object> map = new HashMap<>(15);
        map.put("userType",v);
        RetrofitUtil.getApiService().queryUserPermissionInfo(map)
                .compose(RxSchedulers.compose())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        List<String> dd = (List<String>) baseResponse.getData();
//                        Log.e("TAG",dd.get(0)+"");
                        for(int i=0;i<dd.size();i++){
                            Log.e("TAG",dd.get(i)+"");
                        }

                        Glide.with(getApplicationContext()).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151721118&di=649c9a43aed72fbc4d99ec1a031510c6&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F015c7d574b9f8f6ac72525aee98351.jpg")
                                .thumbnail(0.1f)
                                .into(imageView);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAGs", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    /**
     *
     * @param inSampleSize  可以根据需求计算出合理的inSampleSize
     * */
    public static void compress(int inSampleSize) {
        File sdFile = Environment.getExternalStorageDirectory();
        File originFile = new File(sdFile, "originImg.jpg");
        BitmapFactory.Options options = new BitmapFactory.Options();    //设置此参数是仅仅读取图片的宽高到options中，不会将整张图片读到内存中，防止oom
         options.inJustDecodeBounds = true;
         Bitmap emptyBitmap = BitmapFactory.decodeFile(originFile.getAbsolutePath(), options);
         options.inJustDecodeBounds = false;
         options.inSampleSize = inSampleSize;
         Bitmap resultBitmap = BitmapFactory.decodeFile(originFile.getAbsolutePath(), options);
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
         try {
             FileOutputStream fos = new FileOutputStream(new File(sdFile, "resultImg.jpg"));
             fos.write(bos.toByteArray());
             fos.flush();
             fos.close();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    public void compress(View v) {
        File sdFile = Environment.getExternalStorageDirectory();
        File originFile = new File(sdFile, "originImg.jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(originFile.getAbsolutePath());    //设置缩放比
         int radio = 8;
         Bitmap result = Bitmap.createBitmap(bitmap.getWidth() / radio,
                 bitmap.getHeight() / radio, Bitmap.Config.ARGB_8888);
         Canvas canvas = new Canvas(result);
         RectF rectF = new RectF(0, 0, bitmap.getWidth() / radio, bitmap.getHeight() / radio);    //将原图画在缩放之后的矩形上
         canvas.drawBitmap(bitmap, null, rectF, null);
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         result.compress(Bitmap.CompressFormat.JPEG, 100, bos);
         try {
             FileOutputStream fos = new FileOutputStream(new File(sdFile, "sizeCompress.jpg"));
             fos.write(bos.toByteArray());
             fos.flush();
             fos.close();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }


}
