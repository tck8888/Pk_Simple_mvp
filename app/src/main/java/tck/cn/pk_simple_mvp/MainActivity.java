package tck.cn.pk_simple_mvp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import tck.cn.pk_simple_mvp.model.PhoneInfo;
import tck.cn.pk_simple_mvp.presenter.BasePresenterImpl;
import tck.cn.pk_simple_mvp.presenter.Contract;

public class MainActivity extends AppCompatActivity implements Contract.BaseView, View.OnClickListener {

    private EditText mNumber;
    private TextView mResult;
    private BasePresenterImpl mBasePresenter;
    private ProgressDialog mProgressDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mBasePresenter = new BasePresenterImpl(this);

    }

    private void initView() {
        mNumber = (EditText) findViewById(R.id.number);
        mResult = (TextView) findViewById(R.id.result);
        findViewById(R.id.find).setOnClickListener(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在查询中，请稍后。。。");
    }

    @Override
    public void showData(PhoneInfo phoneInfo) {
        mResult.setText(phoneInfo.getResult().getCity());
        mProgressDialog.hide();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.find:
                mProgressDialog.show();
                mBasePresenter.loadData(mNumber.getText().toString());
                break;
            default:
                break;
        }
    }
}
