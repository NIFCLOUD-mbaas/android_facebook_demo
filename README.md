# 【Android】アプリにFacebookログイン機能をつけよう！
![画像1](/readme-img/Screen1.png)

## 概要
* [ニフクラ mobile backend](https://mbaas.nifcloud.com/)の『SNS連携（Facebook連携）』を利用して、Facebookのログイン機能を実装したサンプルプロジェクトです。
* Facebook連携で取得した会員データは[ニフクラ mobile backend](https://mbaas.nifcloud.com/)の『会員管理機能』として扱うことができます。
 * [【Android】アプリのログイン機能](https://github.com/NIFCLOUD-mbaas/android_login_demo)も用意していますので、参照ください。
* このコンテンツは簡単な操作ですぐに [ニフクラ mobile backend](https://mbaas.nifcloud.com/)の機能を体験いただけます。

## ニフクラ mobile backendについて
スマートフォンアプリのバックエンド機能（プッシュ通知・データストア・会員管理・ファイルストア・SNS連携・位置情報検索・スクリプト）が**開発不要**、しかも基本**無料**(注1)で使えるクラウドサービス！今回はデータストアを体験します。

注1：詳しくは[こちら](https://mbaas.nifcloud.com/price.htm)をご覧ください。

![画像2](/readme-img/Screen2.png)

## 動作環境

* MacOS Monterey version 12.5
* Android Studio Chipmunk | 2021.2.1 Patch 2
* Pixel 2 - Android 13 (Simulator)

※上記内容で動作確認をしています。


## 手順
### 1. [ニフクラ mobile backend](https://mbaas.nifcloud.com/signup.htm)の会員登録とアプリ作成

* 上記リンクから会員登録（無料）をします。登録ができたらログインをすると下図のように「アプリの新規作成」画面が出るのでアプリを作成します。

![画像3](/readme-img/Screen3.png)

* アプリ作成されると下図のような画面になります。
* この２種類のAPIキー（アプリケーションキーとクライアントキー）はAndroidアプリに[ニフクラ mobile backend](https://mbaas.nifcloud.com/)を紐付けるために使用します。

![画像4](/readme-img/Screen4.png)

* 動作確認後に会員情報（アクセストークン）が保存される場所も確認しておきましょう。

![画像5](/readme-img/Screen5.png)

### 2. [GitHub](https://github.com/NIFCLOUD-mbaas/android_facebook_demo.git)からサンプルプロジェクトのダウンロード

* プロジェクトの[Githubページ](https://github.com/NIFCLOUD-mbaas/android_facebook_demo.git)から「Clone or download」＞「Download ZIP」をクリックします。
* プロジェクトを解凍します。

### 3. AndroidStudioでアプリを起動

* AndroidStudioを開き、解凍したプロジェクトを選択します。

![画像6](/readme-img/Screen6.png)

### 4. APIキーの設定

* `MainActivity.java`を編集します。
* 先程[ニフクラ mobile backend](https://mbaas.nifcloud.com/)のダッシュボード上で確認したAPIキーを貼り付けます。

![画像07](/readme-img/Screen7.png)

* それぞれ`YOUR_APPLICATION_KEY`と`YOUR_CLIENT_KEY`の部分を書き換えます。
  * このとき、ダブルクォーテーション（`"`）を消さないように注意してください！


### 5. FacebookSDKを使用するための準備と設定

* [facebook for developers](https://developers.facebook.com/)にログイン（Facebookアカウントがない場合はアカウントを作成）して、右上の「マイアプリ」からの「新しいアプリを追加」を選択します。


![画像0001](/readme-img/fb_0001.png)

* `Build Connected Experiences`を選択して、`Continue`で続きます。

![画像0002](/readme-img/fb_0002.png)

* アプリ名を`App Display Name`に入力してから`Create App`でアプリを作ります。

![画像0003](/readme-img/fb_0003.png)

* `Security Check`の認証のところで`Verify`したら、`Submit`で完了します。

![画像0004](/readme-img/fb_0004.png)

* アプリ管理の画面で作成したアプリが確認できます。

![画像0005](/readme-img/fb_0005.png)

* `アプリID`や`クライアントトークン`の設定します。
  * `/app/src/main/res/values/strings.xml`を編集します。

![画像8](/readme-img/Screen8.png)

* またこの`アプリID`は[ニフクラ mobile backend](https://mbaas.nifcloud.com/)にも設定します。
   * 左側のメニューから、「アプリ設定」を開き、SNS連携の設定を開きます。
   * 連携の許可にチェックを入れた上で、Facebookの開発者向けサイトで発行されたアプリIDを入力してください。
   * 「保存する」をクリックします。

![画像9](/readme-img/Screen9.png)

* 最後に「Basic」をクリックし、「Privacy policy URL」 を貼って、「Save」ボタンを押します。
* その後、上の「In development」にSwitchしますし、ポップアップの「Switch mode」を選びます。
これでアプリの公開は完了します。


![画像14](/readme-img/Screen14.png)

* アプリをFacebookに結合する。
  * 「Add Platform」をクリックしてAndroidのアプリを追加選択します。
  * 「Key Hashes」欄に下記処理から値を取得・設定します。
  * （Windowsの場合）コマンドプロンプトから次のコマンドを入力します。パスワードを聞かれますので 'android' と返します。
  * このとき、opensslコマンドは「openssl場所」/bin/openssl.exeを指定しております。
  * １行目でコマンドを、２行目でパスワードを入力し、３行目でKey Hashが表示されます。

```
>keytool -exportcert -alias androiddebugkey -keystore %HOMEPATH%\.android\debug.keystore | openssl sha1 -binary | openssl base64
>キーストアのパスワードを入力してください: android
>0QYXA5jGgnGg/wco7iNiEXYdeVw=
```

![画像15](/readme-img/Screen15.png)

* アプリをFacebookに結合設定が無い場合はログインエラーになります。

![画像LoginError](/readme-img/LoginError.png)


### 6. 動作確認

* AndroidStudioからビルドする。
    * 「プロジェクト場所」\app\build\outputs\apk\ ***.apk ファイルが生成される。
* apk ファイルをインストールしてシミュレーターが起動したら、Login画面が表示されます。
* __Login__ ボタンをクリックします。
* Facebookログインの画面（ブラウザ）が表示されるので、必要事項を入力し、ログインを行います。
* ログインに成功したらログアウトします。

![画像16](/readme-img/Screen16.png)

-----

* 保存に成功したら、[ニフクラ mobile backend](https://mbaas.nifcloud.com/)のダッシュボードから「会員管理」を確認してみましょう！
* `authData`にはアクセストークンが保存されます。

![画像17](/readme-img/Screen17.png)

* [Facebook](https://www.facebook.com/)にログインして「アプリ」＞「アプリ設定」欄にログインの記録が保存されます。

![画像18](/readme-img/Screen18.png)


操作はここまでです。

## 解説
サンプルプロジェクトに実装済みの内容のご紹介

#### SDKのインポートと初期設定
* ニフクラ mobile backend の[Android ドキュメント（クイックスタート）](https://mbaas.nifcloud.com/doc/current/introduction/quickstart_android.html#/Android/)に開発中のアプリを連携する手順ご用意していますので、ご活用ください。

* [Android用Facebookログイン](https://developers.facebook.com/docs/facebook-login/android)と合わせてご活用ください。

#### ロジック
* FacebookSDKを使用するための処理を`MainActivity.java`に記述しています

```java

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.nifcloud.mbaas.core.NCMB;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBFacebookParameters;
import com.nifcloud.mbaas.core.NCMBUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //**************** APIキーの設定とSDKの初期化 **********************
        NCMB.initialize(this.getApplicationContext(), "YOUR_APPLICATION_KEY", "YOUR_CLIENT_KEY");

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppEventsLogger.activateApp(this.getApplication());

        callbackManager = CallbackManager.Factory.create();
        LoginButton mLoginButton = findViewById(R.id.login_button);
        mLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Login to NIFCLOUD mobile backend
                NCMBFacebookParameters parameters = new NCMBFacebookParameters(
                        loginResult.getAccessToken().getUserId(),
                        loginResult.getAccessToken().getToken(),
                        loginResult.getAccessToken().getExpires()
                );
                try {
                    NCMBUser.loginWith(parameters);
                    Toast.makeText(getApplicationContext(), "Login to NIFCLOUD mbaas with Facebook account", Toast.LENGTH_LONG).show();
                } catch (NCMBException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("tag", "onCancel");
            }

            @Override
            public void onError(@NonNull FacebookException e) {
                // App code
                Log.d("tag", "onError:" + e);
            }
        });
    }
```

* Facebook連携はブラウザのFacebookページに遷移し、行われます。
* Facebookログイン、ログアウトボタン押下時の処理は以下の`com.facebook.login.LoginManager.java`に記述しています

```java

    // ログインボタン押下時の処理
    boolean onActivityResult(int resultCode, Intent data, FacebookCallback<LoginResult>  callback) {
        FacebookException exception = null;
        AccessToken newToken = null;
        LoginClient.Result.Code code = LoginClient.Result.Code.ERROR;
        Map<String, String> loggingExtras = null;
        LoginClient.Request originalRequest = null;

        boolean isCanceled = false;
        if (data != null) {
            LoginClient.Result result =
                    (LoginClient.Result) data.getParcelableExtra(LoginFragment.RESULT_KEY);
            if (result != null) {
                originalRequest = result.request;
                code = result.code;
                if (resultCode == Activity.RESULT_OK) {
                    if (result.code == LoginClient.Result.Code.SUCCESS) {
                        newToken = result.token;
                    } else {
                        exception = new FacebookAuthorizationException(result.errorMessage);
                    }
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    isCanceled = true;
                }
                loggingExtras = result.loggingExtras;
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            isCanceled = true;
            code = LoginClient.Result.Code.CANCEL;
        }

        if (exception == null && newToken == null && !isCanceled) {
            exception = new FacebookException("Unexpected call to LoginManager.onActivityResult");
        }

        boolean wasLoginActivityTried = true;
        Context context = null; //Sadly, there is no way to get activity context at this point.S
        logCompleteLogin(
                context,
                code,
                loggingExtras,
                exception,
                wasLoginActivityTried,
                originalRequest);

        finishLogin(newToken, originalRequest, exception, isCanceled, callback);

        return true;
    }
```

```java

    // ログアウトボタン押下時の処理
    public void logOut() {
        AccessToken.setCurrentAccessToken(null);
        Profile.setCurrentProfile(null);
    }
```

## 参考
### FacebookSDKを使ってFacebook会員認証するのとニフクラ mobile backendSDKを使ってFacebook会員認証するのは何が違うのか？

![画像19](/readme-img/Screen19.png)

* もちろん直接FacebookSDKを呼ぶことも可能ですが、ニフクラ mobile backendSDKを呼べば裏でFacebookSDKを呼んで処理するので、１つ呼べば、Facebookへのログインとニフクラ mobile backendへ会員情報保存が同時に行えるので一石二鳥というわけです。
* また一度会員登録してしまえば、あとはニフクラ mobile backendの会員管理機能で処理が行えるので自前で会員管理システムを構築する必要がなくより楽に開発を行えます。

### もっと深く知りたい方へ
* ニフクラ mobile backend の[ドキュメント（SNS連携：Facebook連携）](https://mbaas.nifcloud.com/doc/current/sns/facebook_android.html#/Android/)をご用意していますので、ご活用ください。
* Facebook開発の [ドキュメント(Android用Facebookログイン)](https://developers.facebook.com/docs/facebook-login/android)参照ください。
