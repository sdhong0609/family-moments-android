# Family Moments - 팀 프로젝트
⚠️ 현재 서버 기한 만료로 앱이 정상작동하지 않습니다.

<br>

## 📝 프로젝트 소개
* Family Moments는 네이버 밴드처럼 가족만 초대하여 사진과 글을 공유하고 소통할 수 있는 폐쇄형 SNS 형태의 안드로이드 앱 서비스입니다.
* 홈 화면에서 가족 구성원들이 업로드한 포스트 목록을 볼 수 있습니다. 포스트를 클릭하면 포스트 상세페이지로 이동합니다.
* 앨범 화면에서 가족 구성원들이 업로드한 사진 목록을 그리드 형태로 볼 수 있습니다.
* 캘린더 화면에서 날짜 별로 작성된 포스트가 있는지 확인 가능합니다. 날짜를 클릭하면 해당 날짜에 작성된 글을 확인할 수 있습니다.

<br>

## 👥 팀원 구성
기획 1명, 디자인 1명, 서버 5명, Android 3명

<br>

## ✅ 수행 업무
👇 **제가 이 프로젝트에서 수행한 업무입니다. 다른 팀원들의 수행업무는 포함되어 있지 않습니다.**
* 메인 화면(MainScreen)
    * NavHost 및 BottomNavigation을 활용하여 하단 탭과 각 화면(Home, Album, Calendar 등)을 연동하였습니다.
* 홈 화면(HomeScreen)
    * LazyColumn을 사용하여 작성된 포스트(사진+글) 아이템을 목록형 UI로 구현하였습니다.
* 캘린더 화면(CalendarScreen)
    * LazyVerticalGrid를 활용하여 커스텀 캘린더 UI를 구현하였습니다.
    * API 응답으로 전달받은 글 목록을 기반으로 해당 날짜에 점을 표시하여, 글 존재 여부를 쉽게 파악할 수 있도록 구현하였습니다.
    * 날짜를 클릭 시 상세화면(CalendarDayScreen)으로 이동하여 해당 날짜에 작성된 글을 확인할 수 있도록 구현하였습니다.
* 앨범 화면(AlbumScreen)
    * 가족 간 공유된 사진을 Grid 형태로 나열하였고, 클릭 시 사진을 크게 볼 수 있는 팝업을 구현하였습니다.
* QA를 통해 확인된 각종 버그 수정

<br>

## 🤖 Android 기술 스택

| 카테고리         | 기술 스택                                         |
|----------------|------------------------------------------------|
| UI             | Jetpack Compose                                |
| Architecture   | MVVM, Single Module                            |
| DI             | Hilt                                           |
| Network        | Retrofit                                       |
| Image          | Coil                                           |
| Asynchronous   | Coroutines, Flow                               |
| Jetpack        | ViewModel, Navigation                          |

<br>

## 📷 스크린샷
👇 **제가 개발한 기능 위주의 스크린샷입니다**
|![Screenshot_20240720_230025_FamilyMoments(Debug)](https://github.com/user-attachments/assets/c288b3a8-6ee1-432e-a32c-a7a74a413428) |![Screenshot_20240720_230042_FamilyMoments(Debug)](https://github.com/user-attachments/assets/0c0e7187-ca86-48ad-9671-119e343134ee) |![Screenshot_20240720_230100_FamilyMoments(Debug)](https://github.com/user-attachments/assets/2336ecd0-48e9-4803-94db-38999bd4cced) |
|-|-|-|

<br>

## 📺 데모 영상
👇 **제가 개발한 기능을 담은 영상입니다**
<div align="center">
  <video src="https://github.com/user-attachments/assets/c778e806-ce12-48ff-9665-58ea9593b24c" />
</div>
