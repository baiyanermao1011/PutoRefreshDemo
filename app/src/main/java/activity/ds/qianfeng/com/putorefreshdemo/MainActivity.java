package activity.ds.qianfeng.com.putorefreshdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements PullToRefreshBase.OnRefreshListener2<ListView> {

    private PullToRefreshListView refresh;
    private int page;
    private ArrayAdapter<String> adapter;
    private Handler handler=new Handler() {
       public void handleMessage(Message msg){
           switch (msg.what){
               case 0:
                   List<String> strings=new ArrayList<>();
                   for(int i=0;i<30;i++){
                       strings.add(String.format("第%02d页--第%03d条数据",page,i));
                   }
                   adapter.addAll(strings);
                   break;
               case 1:
                   List<String> strings1=new ArrayList<>();
                   for(int i=0;i<30;i++){
                       strings1.add(String.format("第%02d页--第%03d条数据",page,i));
                   }
                   adapter.addAll(strings1);
                   break;
           }
           //刷新
           refresh.onRefreshComplete();
       }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refresh = (PullToRefreshListView) findViewById(R.id.pulltorefresh_listview);
        List<String> strings=new ArrayList<>();
        for(int i=0;i<30;i++){
            strings.add(String.format("第%02d页--第%03d条数据",page,i));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,strings);
        refresh.setAdapter(adapter);
        refresh.setMode(PullToRefreshBase.Mode.BOTH);
        refresh.setOnRefreshListener(this);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        adapter.clear();
        page=0;
        //延时发送信息
        handler.sendEmptyMessageDelayed(0,2000);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        handler.sendEmptyMessageDelayed(1,3000);
    }
}
