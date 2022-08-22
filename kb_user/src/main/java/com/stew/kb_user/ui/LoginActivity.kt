package com.stew.kb_user.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.stew.kb_common.base.BaseActivity
import com.stew.kb_common.util.Constants
import com.stew.kb_common.util.KVUtil
import com.stew.kb_common.util.ToastUtil
import com.stew.kb_user.R
import com.stew.kb_user.databinding.ActivityLoginBinding
import com.stew.kb_user.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by stew on 8/21/22.
 * mail: stewforani@gmail.com
 */
@Route(path = Constants.PATH_LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun getLayoutID(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        loginViewModel.loginData.observe(this, {
            dismissLoadingDialog()
            ToastUtil.showMsg("登陆成功！")
            finish()
            KVUtil.put(Constants.USER_ID,it.id)
            KVUtil.put(Constants.USER_NAME,it.username)
        })

        mBind.txLogin.setOnClickListener {
            if (mBind.edit1.text.isNotEmpty() && mBind.edit2.text.isNotEmpty()) {
                showLoadingDialog()
                loginViewModel.login(mBind.edit1.text.toString(), mBind.edit2.text.toString())
            } else {
                ToastUtil.showMsg("输入有误！")
            }
        }

        mBind.imgBack.setOnClickListener { finish() }


    }

}