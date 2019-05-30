package com.tyj.craftshow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.tyj.craftshow.activity.BroadCastMain2Activity;
import com.tyj.craftshow.http.BaseResponse;
import com.tyj.craftshow.http.RetrofitUtil;
import com.tyj.craftshow.model.DemoActivity;
import com.tyj.craftshow.util.RxClickUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.tyj.craftshow.http.RxSchedulers.compose;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_tvDate)
    TextView tvDate;

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
        mViewPager.setVisibility(View.VISIBLE);
        inItView();
    }

    @SuppressLint("CheckResult")
    public void inItView() {

        long currentTime = System.currentTimeMillis();
        String timeNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
        tvDate.setText(timeNow);

        RxClickUtil.clicks(btn1).subscribe(o -> {
            startActivity(new Intent(this, DemoActivity.class));
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPostRequest("业主项目部负责人");//测试网络请求
            }
        });
        list.add("https://github.com/li20151102/TestPic/raw/master/11.jpg");
        list.add("https://github.com/li20151102/TestPic/raw/master/12.jpg");
        list.add("https://github.com/li20151102/TestPic/raw/master/13.jpeg");
        list.add("https://github.com/li20151102/TestPic/raw/master/14.jpeg");
        list.add("https://github.com/li20151102/TestPic/raw/master/15.jpeg");
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
//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(1000,1000);
//            imageView.setLayoutParams(lp);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //设置到容器,也就是ViewPager
//            ImageLoader.getInstance().displayImage(list.get(position%list.size()),imageView);
            Glide.with(context).load(list.get(position%list.size()))
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                    .apply(new RequestOptions().priority(Priority.IMMEDIATE)) //指定加载的优先级，优先级越高越优先加载
                    .apply(new RequestOptions().error(R.mipmap.ic_launcher_round)) //指定加载的优先级，优先级越高越优先加载
                    .thumbnail(0.5f)
                    .into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("TAG",position+"");
                    if(position==3){
                        startActivity(new Intent(MainActivity.this, BroadCastMain2Activity.class));
                    }
                }
            });
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

    public class MyBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String D = intent.getStringExtra("hh");
            Log.e("TAG","DDD"+D);
        }
    }

    @SuppressLint("CheckResult")
    private void setPostRequest(String v) {
        Map<String,Object> map = new HashMap<>(15);
        map.put("userType",v);
        RetrofitUtil.getApiService().queryUserPermissionInfo(map)
                .compose(compose())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        List<String> dd = (List<String>) baseResponse.getData();
                        for(int i=0;i<dd.size();i++){
                            Log.e("TAG",dd.get(i)+"");
                        }
                        imageView.setVisibility(View.VISIBLE);
                        mViewPager.setVisibility(View.GONE);
                        try {
                            //不知为何无效getBitmapFormUri
                            Glide.with(getApplicationContext()).load(getBitmapFormUri(MainActivity.this, Uri.parse("http://sowcar.com/t6/694/1554169906x1707632075.jpg")))
                                    .into(imageView);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
     * 通过uri获取图片并进行压缩
     *
     * @param uri
     */
    public static Bitmap getBitmapFormUri(Activity ac, Uri uri) throws FileNotFoundException, IOException {
        InputStream input = ac.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;
        //图片分辨率以480x800为标准
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例压缩
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        input = ac.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return compressImage(bitmap);//再进行质量压缩
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }


}
