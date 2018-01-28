<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <br><br>
        <!--logo图-->
        <div class="center-align"><img src="imgs/logo-big.png" alt="" >
        </div>
        <!--表单-->
        <div class="row ">
            <form class="col s8 offset-s4" id="loginForm" action="/session" method="post">
                <div class="row">
                    <div class="input-field col s6">
                        <i class="material-icons prefix pink-text text-accent-1">phone</i>
                        <input id="mobileNo" name="mobileNo" type="tel" class="validate" required length="11">
                        <label for="mobileNo" >手机号码</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <i class="material-icons prefix pink-text text-accent-1">lock</i>
                        <input id="password" name="password" type="password" class="validate" required length="18">
                        <label for="password" >密码</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s4">
                        <i class="material-icons prefix pink-text text-accent-1 ">mode_edit</i>
                        <input id="inputCaptcha" name="inputCaptcha" type="tel" length="4" minlength="4" maxlength="4" required>
                        <label for="inputCaptcha">验证码</label>
                    </div>

                    <div class="input-field col s2">
                        <img src="/user/gifCode" id="captcha">
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s6">
                        <div class="input-field col 11 right">
                            <a href="" class="pink-text text-accent-1">忘记密码</a>
                        </div>
                        <input type="checkbox" class="filled-in pink accent-1" checked="checked" name="rememberMe" id="filled-in-box" value="rememberMe"  />
                        <label for="filled-in-box">记住我</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s3">
                        <a href="/register.html" class="pink-text text-accent-1">返回注册页面</a>
                    </div>
                    <div class="input-field col 6">
                        <button class="btn waves-effect waves-light pink accent-1" type="submit" id="submitForm" name="action">立即登陆
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <br><br>
    </div>
</div>
