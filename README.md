# doxygenExample

### Description
- Repository Main Brench Tree 구조

```plantuml
@startwbs
title 
[ Repository Directory Tree ]
end title
+ Repository
++ Main
+++ config_file
++++_ DoxygenWizard 설정파일 Doxyfile_cfg2_srs_sdd_example
++++_ 독시젠위저드로 생성시 외부 라이브러리 기능 지원시 \nnew_header.html 파일에 링크 추가해 주고 생성하면됨
+++ logoimg
++++_ 리트빅 회사 로고 이미지 파일: litbig-logo-50.png
+++ main_page
++++_ 마크다운페이지 파일 저장 \n main_page.md
++++_ SDD.md
++++_ SRS.md
++++_ tips.md
+++ mermaid_uml
++++_ mermaid UML 툴로 생성한 파일들 저장
+++ output/html
++++_ 독시젠위저드를 통하여 생성한 문서들이 있는 html 디렉토리
+++ source/android_hvac/main
++++_ 구현 소스 파일 저장 디렉토리
+++ uml
++++_ Plant UML 툴을 사용하여 생성한 파일들 저장
+++_ Readme.md

@endwbs
```
-------------------------------------------------------

### 작성및 관리
- Github 에 Doxygen 산출물과 개발소스코드 등록하고 편집/갱신시 파일 관리토록 하고<br> Local Web Server에 DoxygenWizard로 갱신한 파일들을 올려<br> 노션이나 브라우져에서 갱신된 문서 내용을 확인 할 수 있도록 하자
- 기능별 산출물 작성시 마크다운 페이지 파일을 추가해서 작성 하는것을 권장함
- source/ 폴더 아래에 개발중인 prototyping 코드파일이나 폴더들을 추가 저장 한다  
- main_page 폴더에 신규 기능들에 대한 SRS/SDD markdown 페이지 파일을 추가해 주고<br> visual studio code 편집기를 사용하여 작성 하도록 하고<br> Notion에 공유된 Doxygen_setting 가이드를 참고하여<br> doxygenwizard -> Export -> Input 항목에 추가한 파일 위치를 추가해 준다

-------------------------------------------------------

## 업데이트 이력

### [Updated] 2022-10-18 
- registered generated doxygen example document <br>
-------------------------------------------------------



