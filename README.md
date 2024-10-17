# websockets-quickstart

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## このプロジェクトの作り方

以下のコマンドによって作成される

`quarkus create app org.acme:websockets-quickstart \
--extension='websockets' \
--no-code --kotlin --gradle`

`--no-code`はデフォルトのコードテンプレートを使用しないと言う意味

`--kotlin`はKotlin言語を使用すると言う意味

`--gradle`はGradleをビルドツールとして使用すると言う意味

## webソケットのパッケージ導入

以下のコマンドを使う

`quarkus extension add websockets-next`

このコマンドを実行後、Android Studioの画面右上に表示されている**Gradleアイコン**（**斜め左下の矢印があるもの**）をタップして、同期させること！

なお、webソケットについてはこの記事を参照

[今さら聞けないWebSocket ~ WebSocketとは ~ ](https://qiita.com/chihiro/items/9d280704c6eff8603389)

[WebSocket 入門](https://zenn.dev/nameless_sn/articles/websocket_tutorial)

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.native.enabled=true
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/websockets-quickstart-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/gradle-tooling>.

## Related Guides

- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin
- WebSockets ([guide](https://quarkus.io/guides/websockets)): WebSocket communication channel support
