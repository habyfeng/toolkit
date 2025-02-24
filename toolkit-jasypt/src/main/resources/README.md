## 加密字符串的生成方式

```sh
java -cp repository\org\jasypt\jasypt\1.9.3\jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input=12qwaszx password="zz919zz" algorithm=PBEWithMD5AndDES

```

> 备注：命令行方式algorithm=PBEWITHHMACSHA512ANDAES_256会报错```"Operation not possible (Bad input or parameters)"```