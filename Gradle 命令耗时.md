# Gradle 命令耗时


#### 全新编译(含buildJni)

###### gradlew makeDevApk (含 buildDevLibs) Total time: 4 mins 42.567 secs

 Building 100%Total time: 4 mins 42.567 secs
> Building 100% Task timings:
    872ms  :upwallet:clean
  55443ms  :upwallet:makeDevApk
     10ms  :upshare:preBuild
      7ms  :upshare:preReleaseBuild
     25ms  :upshare:compileReleaseNdk
      4ms  :upshare:compileLint
     21ms  :upshare:copyReleaseLint
     43ms  :upshare:mergeReleaseProguardFiles
     17ms  :upshare:packageReleaseRenderscript
      5ms  :upshare:checkReleaseManifest
     16ms  :upshare:prepareReleaseDependencies
     89ms  :upshare:compileReleaseRenderscript
     29ms  :upshare:generateReleaseResValues
      6ms  :upshare:generateReleaseResources
     33ms  :upshare:packageReleaseResources
     18ms  :upshare:compileReleaseAidl
     25ms  :upshare:generateReleaseBuildConfig
     26ms  :upshare:mergeReleaseShaders
     27ms  :upshare:compileReleaseShaders
      7ms  :upshare:generateReleaseAssets
     21ms  :upshare:mergeReleaseAssets
     20ms  :upshare:processReleaseManifest
     27ms  :upshare:processReleaseResources
      6ms  :upshare:generateReleaseSources
     26ms  :upshare:incrementalReleaseJavaCompilationSafeguard
    119ms  :upshare:compileReleaseJavaWithJavac
     13ms  :upshare:processReleaseJavaRes
     70ms  :upshare:transformResourcesWithMergeJavaResForRelease
     30ms  :upshare:transformClassesAndResourcesWithSyncLibJarsForRelease
     19ms  :upshare:mergeReleaseJniLibFolders
     31ms  :upshare:transformNative_libsWithMergeJniLibsForRelease
     15ms  :upshare:transformNative_libsWithSyncJniLibsForRelease
     31ms  :upshare:bundleRelease
      5ms  :uptsm:preBuild
      4ms  :uptsm:preReleaseBuild
     10ms  :uptsm:compileReleaseNdk
      4ms  :uptsm:compileLint
      9ms  :uptsm:copyReleaseLint
     10ms  :uptsm:mergeReleaseProguardFiles
      8ms  :uptsm:packageReleaseRenderscript
      4ms  :uptsm:checkReleaseManifest
      4ms  :uptsm:prepareReleaseDependencies
     27ms  :uptsm:compileReleaseRenderscript
      9ms  :uptsm:generateReleaseResValues
      4ms  :uptsm:generateReleaseResources
     19ms  :uptsm:packageReleaseResources
     23ms  :uptsm:compileReleaseAidl
     14ms  :uptsm:generateReleaseBuildConfig
     16ms  :uptsm:mergeReleaseShaders
     12ms  :uptsm:compileReleaseShaders
      5ms  :uptsm:generateReleaseAssets
     17ms  :uptsm:mergeReleaseAssets
     12ms  :uptsm:processReleaseManifest
     18ms  :uptsm:processReleaseResources
      5ms  :uptsm:generateReleaseSources
     25ms  :uptsm:incrementalReleaseJavaCompilationSafeguard
    434ms  :uptsm:compileReleaseJavaWithJavac
      8ms  :uptsm:processReleaseJavaRes
     29ms  :uptsm:transformResourcesWithMergeJavaResForRelease
     44ms  :uptsm:transformClassesAndResourcesWithSyncLibJarsForRelease
     12ms  :uptsm:mergeReleaseJniLibFolders
     21ms  :uptsm:transformNative_libsWithMergeJniLibsForRelease
     11ms  :uptsm:transformNative_libsWithSyncJniLibsForRelease
     25ms  :uptsm:bundleRelease
      5ms  :upwallet:preBuild
      4ms  :upwallet:preUnionpayDevelopBuild
      3ms  :upwallet:checkUnionpayDevelopManifest
      3ms  :upwallet:preAaptOptionsBuild
      4ms  :upwallet:preDebugBuild
      6ms  :upwallet:preLintOptionsBuild
      4ms  :upwallet:preReleaseBuild
      5ms  :upwallet:preUnionpayProductBuild
      5ms  :upwallet:preUnionpayTestBuild
     85ms  :upwallet:prepareWl_androidUpshareUnspecifiedLibrary
     57ms  :upwallet:prepareWl_androidUptsmUnspecifiedLibrary
      4ms  :upwallet:prepareUnionpayDevelopDependencies
    443ms  :upwallet:compileUnionpayDevelopAidl
     53ms  :upwallet:compileUnionpayDevelopRenderscript
     54ms  :upwallet:generateUnionpayDevelopBuildConfig
     40ms  :upwallet:mergeUnionpayDevelopShaders
     25ms  :upwallet:compileUnionpayDevelopShaders
      3ms  :upwallet:generateUnionpayDevelopAssets
     57ms  :upwallet:mergeUnionpayDevelopAssets
     14ms  :upwallet:generateUnionpayDevelopResValues
      2ms  :upwallet:generateUnionpayDevelopResources
   7876ms  :upwallet:mergeUnionpayDevelopResources
    228ms  :upwallet:processUnionpayDevelopManifest
   2796ms  :upwallet:processUnionpayDevelopResources
      2ms  :upwallet:generateUnionpayDevelopSources
     36ms  :upwallet:incrementalUnionpayDevelopJavaCompilationSafeguard
  12281ms  :upwallet:compileUnionpayDevelopJavaWithJavac
     15ms  :upwallet:compileUnionpayDevelopNdk
      3ms  :upwallet:compileUnionpayDevelopSources
     30ms  :upwallet:prePackageMarkerForUnionpayDevelop
     15ms  :upwallet:processUnionpayDevelopJavaRes
    296ms  :upwallet:transformResourcesWithMergeJavaResForUnionpayDevelop
 171303ms  :upwallet:transformClassesAndResourcesWithProguardForUnionpayDevelop
  18869ms  :upwallet:transformClassesWithDexForUnionpayDevelop
     41ms  :upwallet:mergeUnionpayDevelopJniLibFolders
     93ms  :upwallet:transformNative_libsWithMergeJniLibsForUnionpayDevelop
      9ms  :upwallet:validateDevelopConfigSigning
   1675ms  :upwallet:packageUnionpayDevelop
     11ms  :upwallet:assembleUnionpayDevelop



###### gradlew -q makeTestApk (含 buildTestLibs) 

> Building 100%Total time: 3 mins 56.241 secs
> Building 100% Task timings:
   1223ms  :upwallet:clean
  10745ms  :upwallet:makeTestApk
      8ms  :upshare:preBuild
      6ms  :upshare:preReleaseBuild
     19ms  :upshare:compileReleaseNdk
      6ms  :upshare:compileLint
     18ms  :upshare:copyReleaseLint
     56ms  :upshare:mergeReleaseProguardFiles
     15ms  :upshare:packageReleaseRenderscript
      6ms  :upshare:checkReleaseManifest
     11ms  :upshare:prepareReleaseDependencies
     53ms  :upshare:compileReleaseRenderscript
     32ms  :upshare:generateReleaseResValues
      5ms  :upshare:generateReleaseResources
     36ms  :upshare:packageReleaseResources
     24ms  :upshare:compileReleaseAidl
     26ms  :upshare:generateReleaseBuildConfig
     20ms  :upshare:mergeReleaseShaders
     27ms  :upshare:compileReleaseShaders
      6ms  :upshare:generateReleaseAssets
     21ms  :upshare:mergeReleaseAssets
     21ms  :upshare:processReleaseManifest
     33ms  :upshare:processReleaseResources
      6ms  :upshare:generateReleaseSources
     30ms  :upshare:incrementalReleaseJavaCompilationSafeguard
     97ms  :upshare:compileReleaseJavaWithJavac
     11ms  :upshare:processReleaseJavaRes
     25ms  :upshare:transformResourcesWithMergeJavaResForRelease
     37ms  :upshare:transformClassesAndResourcesWithSyncLibJarsForRelease
     17ms  :upshare:mergeReleaseJniLibFolders
     20ms  :upshare:transformNative_libsWithMergeJniLibsForRelease
     13ms  :upshare:transformNative_libsWithSyncJniLibsForRelease
     38ms  :upshare:bundleRelease
      6ms  :uptsm:preBuild
      6ms  :uptsm:preReleaseBuild
     13ms  :uptsm:compileReleaseNdk
      4ms  :uptsm:compileLint
      9ms  :uptsm:copyReleaseLint
     16ms  :uptsm:mergeReleaseProguardFiles
     11ms  :uptsm:packageReleaseRenderscript
      5ms  :uptsm:checkReleaseManifest
      6ms  :uptsm:prepareReleaseDependencies
     19ms  :uptsm:compileReleaseRenderscript
     11ms  :uptsm:generateReleaseResValues
      5ms  :uptsm:generateReleaseResources
     22ms  :uptsm:packageReleaseResources
     20ms  :uptsm:compileReleaseAidl
     20ms  :uptsm:generateReleaseBuildConfig
     20ms  :uptsm:mergeReleaseShaders
     13ms  :uptsm:compileReleaseShaders
      6ms  :uptsm:generateReleaseAssets
     20ms  :uptsm:mergeReleaseAssets
     11ms  :uptsm:processReleaseManifest
     24ms  :uptsm:processReleaseResources
      6ms  :uptsm:generateReleaseSources
     22ms  :uptsm:incrementalReleaseJavaCompilationSafeguard
    476ms  :uptsm:compileReleaseJavaWithJavac
     11ms  :uptsm:processReleaseJavaRes
     22ms  :uptsm:transformResourcesWithMergeJavaResForRelease
     48ms  :uptsm:transformClassesAndResourcesWithSyncLibJarsForRelease
     13ms  :uptsm:mergeReleaseJniLibFolders
     23ms  :uptsm:transformNative_libsWithMergeJniLibsForRelease
     13ms  :uptsm:transformNative_libsWithSyncJniLibsForRelease
     25ms  :uptsm:bundleRelease
      5ms  :upwallet:preBuild
      4ms  :upwallet:preUnionpayTestBuild
      4ms  :upwallet:checkUnionpayTestManifest
      4ms  :upwallet:preAaptOptionsBuild
      5ms  :upwallet:preDebugBuild
      4ms  :upwallet:preLintOptionsBuild
      3ms  :upwallet:preReleaseBuild
      4ms  :upwallet:preUnionpayDevelopBuild
      4ms  :upwallet:preUnionpayProductBuild
     91ms  :upwallet:prepareWl_androidUpshareUnspecifiedLibrary
     61ms  :upwallet:prepareWl_androidUptsmUnspecifiedLibrary
      5ms  :upwallet:prepareUnionpayTestDependencies
    410ms  :upwallet:compileUnionpayTestAidl
     52ms  :upwallet:compileUnionpayTestRenderscript
     49ms  :upwallet:generateUnionpayTestBuildConfig
     43ms  :upwallet:mergeUnionpayTestShaders
     24ms  :upwallet:compileUnionpayTestShaders
      2ms  :upwallet:generateUnionpayTestAssets
     60ms  :upwallet:mergeUnionpayTestAssets
     12ms  :upwallet:generateUnionpayTestResValues
      3ms  :upwallet:generateUnionpayTestResources
   7279ms  :upwallet:mergeUnionpayTestResources
    203ms  :upwallet:processUnionpayTestManifest
   2774ms  :upwallet:processUnionpayTestResources
      2ms  :upwallet:generateUnionpayTestSources
     33ms  :upwallet:incrementalUnionpayTestJavaCompilationSafeguard
  12579ms  :upwallet:compileUnionpayTestJavaWithJavac
      9ms  :upwallet:compileUnionpayTestNdk
      3ms  :upwallet:compileUnionpayTestSources
     44ms  :upwallet:prePackageMarkerForUnionpayTest
     14ms  :upwallet:processUnionpayTestJavaRes
    265ms  :upwallet:transformResourcesWithMergeJavaResForUnionpayTest
 170899ms  :upwallet:transformClassesAndResourcesWithProguardForUnionpayTest
  17323ms  :upwallet:transformClassesWithDexForUnionpayTest
     40ms  :upwallet:mergeUnionpayTestJniLibFolders
     76ms  :upwallet:transformNative_libsWithMergeJniLibsForUnionpayTest
      9ms  :upwallet:validateTestConfigSigning
   1664ms  :upwallet:packageUnionpayTest
    331ms  :upwallet:zipalignUnionpayTest
     10ms  :upwallet:assembleUnionpayTest


###### gradlew -q makeProdApk 266833 4m 23s
>生成 PRODUCT_BUILD 环境下APK成功
>
Task timings:
  53214ms  :upwallet:makeProdApk
     55ms  :upshare:compileReleaseRenderscript
     57ms  :upshare:processReleaseManifest
    223ms  :upshare:processReleaseResources
    665ms  :upshare:compileReleaseJavaWithJavac
    107ms  :upshare:transformClassesAndResourcesWithSyncLibJarsForRelease
     56ms  :upshare:bundleRelease
    351ms  :uptsm:compileReleaseAidl
    193ms  :uptsm:processReleaseResources
   1825ms  :uptsm:compileReleaseJavaWithJavac
    199ms  :uptsm:transformClassesAndResourcesWithSyncLibJarsForRelease
    367ms  :upwallet:compileUnionpayProductAidl
   6963ms  :upwallet:mergeUnionpayProductResources
    105ms  :upwallet:processUnionpayProductManifest
    996ms  :upwallet:processUnionpayProductResources
   9953ms  :upwallet:compileUnionpayProductJavaWithJavac
    188ms  :upwallet:transformResourcesWithMergeJavaResForUnionpayProduct
 171930ms  :upwallet:transformClassesAndResourcesWithProguardForUnionpayProduct
  15807ms  :upwallet:transformClassesWithDexForUnionpayProduct
   1775ms  :upwallet:transformClassesWithShrinkResForUnionpayProduct
    435ms  :upwallet:mergeUnionpayProductJniLibFolders
   1369ms  :upwallet:packageUnionpayProduct



#### buildJni

###### gradlew -q buildDevLibs 53s

>DEVELOP_BUILD 环境下的So文件编译 成功 
Task timings:
    377ms  :upwallet:clean
  53416ms  :upwallet:buildDevLibs


###### gradlew -q buildTestLibs

>TEST_BUILD 环境下的So文件编译 成功  10s
Task timings:
  10057ms  :upwallet:buildTestLibs


###### gradlew -q buildProdLibs

>PRODUCT_BUILD 环境下的So文件编译 成功 9s
Task timings:
   9814ms  :upwallet:buildProdLibs



#### 二次编译（不含buildJni）

###### gradlew -q makeDevApk 3m 30s

>生成 DEVELOP_BUILD 环境下APK成功
makeDevApk assembleUnionpayDevelop task Build Succsess ...
Task timings:
     50ms  :upwallet:makeDevApk
     79ms  :uptsm:compileReleaseJavaWithJavac
     61ms  :upwallet:prepareWl_androidUpshareUnspecifiedLibrary
    405ms  :upwallet:compileUnionpayDevelopAidl
     53ms  :upwallet:mergeUnionpayDevelopAssets
   7048ms  :upwallet:mergeUnionpayDevelopResources
    142ms  :upwallet:processUnionpayDevelopManifest
   1036ms  :upwallet:processUnionpayDevelopResources
  11048ms  :upwallet:compileUnionpayDevelopJavaWithJavac
    279ms  :upwallet:transformResourcesWithMergeJavaResForUnionpayDevelop
 175250ms  :upwallet:transformClassesAndResourcesWithProguardForUnionpayDevelop
  15058ms  :upwallet:transformClassesWithDexForUnionpayDevelop
   1697ms  :upwallet:packageUnionpayDevelop


###### gradlew -q makeTestApk 3m30s

生成 TEST_BUILD 环境下APK成功
>makeTestApk assembleUnionpayTest task Build Succsess ...
Task timings:
     75ms  :upwallet:makeTestApk
     52ms  :upshare:compileReleaseRenderscript
     52ms  :upshare:processReleaseManifest
    259ms  :upshare:processReleaseResources
    617ms  :upshare:compileReleaseJavaWithJavac
    110ms  :upshare:transformClassesAndResourcesWithSyncLibJarsForRelease
     52ms  :upshare:bundleRelease
    350ms  :uptsm:compileReleaseAidl
    187ms  :uptsm:processReleaseResources
   2007ms  :uptsm:compileReleaseJavaWithJavac
    207ms  :uptsm:transformClassesAndResourcesWithSyncLibJarsForRelease
    358ms  :upwallet:compileUnionpayTestAidl
   7183ms  :upwallet:mergeUnionpayTestResources
    108ms  :upwallet:processUnionpayTestManifest
   1011ms  :upwallet:processUnionpayTestResources
   9425ms  :upwallet:compileUnionpayTestJavaWithJavac
    195ms  :upwallet:transformResourcesWithMergeJavaResForUnionpayTest
 172275ms  :upwallet:transformClassesAndResourcesWithProguardForUnionpayTest
  16527ms  :upwallet:transformClassesWithDexForUnionpayTest
   1648ms  :upwallet:packageUnionpayTest
    261ms  :upwallet:zipalignUnionpayTest

###### gradlew -q makeProdApk 3m30s

生成 PRODUCT_BUILD 环境下APK成功
>makeProdApk assembleUnionpayProduct task Build Succsess ...
Task timings:
     56ms  :upwallet:makeProdApk
     51ms  :upshare:compileReleaseRenderscript
     59ms  :upshare:processReleaseManifest
    255ms  :upshare:processReleaseResources
    609ms  :upshare:compileReleaseJavaWithJavac
    108ms  :upshare:transformClassesAndResourcesWithSyncLibJarsForRelease
     58ms  :upshare:bundleRelease
    345ms  :uptsm:compileReleaseAidl
    184ms  :uptsm:processReleaseResources
   1836ms  :uptsm:compileReleaseJavaWithJavac
    204ms  :uptsm:transformClassesAndResourcesWithSyncLibJarsForRelease
    386ms  :upwallet:compileUnionpayProductAidl
   6861ms  :upwallet:mergeUnionpayProductResources
    105ms  :upwallet:processUnionpayProductManifest
   1026ms  :upwallet:processUnionpayProductResources
   9197ms  :upwallet:compileUnionpayProductJavaWithJavac
    195ms  :upwallet:transformResourcesWithMergeJavaResForUnionpayProduct
 172427ms  :upwallet:transformClassesAndResourcesWithProguardForUnionpayProduct
  18570ms  :upwallet:transformClassesWithDexForUnionpayProduct
   1763ms  :upwallet:transformClassesWithShrinkResForUnionpayProduct
   1385ms  :upwallet:packageUnionpayProduct



###### gradlew -i makeChannelApks 2m20s

> Building 100%Total time: 2 mins 25.844 secs
> Building 100%Task timings:
 138898ms  :upwallet:makeChannelApks