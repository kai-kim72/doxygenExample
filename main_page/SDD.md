@page sdd_example SDD Example
@tableofcontents
# SDD

# Software Detailed Design of  XXX

## Revision History

| Date  | Version | Descriptions | Writer |
| --- | --- | --- | --- |
| 2022,10,17 | 0.9 |  hvac sdd draft version | kai.kim |
|  |  |  |  |

# Contents

# 1. 개요 (Introduction)

## 1.1 개발 목적 (Purpose)

## 1.2 개발 범위 (Scope)

# 2. 참조

## 2.1 참조 문서 (Reference)

## 2.2 용어 및 약어표 (Abbreviation)

| Items | Descriptions |
| --- | --- |
| HVAC | (heating, ventilation, & air conditioning)는 난방, 환기, 냉방 즉 이들을 통합하여 실내 및 자동차 환경의 안락을 위해 쓰이는 기술 |
|  |  |

# 3. 시스템 아키텍쳐 (System Architecture)

## 3.1 Physical Architecture Overview

- 

## 3.2 Hardware Component Description

- 시스템 블럭도를 명시하고 시스템에 대한 간략한 설명과 SW가 따라야 할 시스템 요구 사항등을 기술한다

## 3.3 System environment

- 시스템 사양등 특징을 기술한다

## 3.4 External Interface

- 시스템상의 HW, SW 인터페이스를 기술한다

## 3.5 Hardware interface

- For PEMS/Electro-medical Devices, add requirements about integration of software and hardware.

## 3.5 Network interface

- add here communication and networks stuff, like IP, wireless, Bluetooth …

# 4. 소프트웨어 설계 (Software Detailed Design)

## 4.1 Logical Architecture Overview

- Describe the top level software components and their interactions/relationships. Use UML package diagrams and/or layer diagrams and/or interface diagrams. Describe also the operating systems on which the software runs.
- mermaid 사용하여 그린 예 ( architecture 예제 부족 )

<div class="mermaid">
graph TD; subgraph User Space; A(Applications) --> A5(HVAC App);A --> A1[home];A --> A2(Contacts);A --> A3(Phone);A --> A4(browser);A5 --> A51(DriverSeat);A5 --> A52(PassengerSeat);A5 --> A53(SteerWheel);A5 --> A54(AirConditioner);A5 --> A56(FWUpdatemanager);A5 --> A57(ILiftable);A5 --> A58(IVolumeable);A5 --> A59(ILevelAdjustable);A5 --> A510(Heatable);A5 --> A511(Coolable);  end;A1 --> B0(Application Framework);A2 --> B0;A3 --> B0;A4 --> B0;A51 --> B0;A52 --> B0; A53 --> B0;A54 --> B0;A56 --> B0;A57 --> B0;A58 --> B0;A59 --> B0;A510 --> B0;A511 --> B0;subgraph Applcation Framework;B0 --> B1(ActivityManager);B0 --> B2(Window Manager);B0 --> B3(Content Provider);B0 --> B4(View System);B0 --> B5(Package Manager);B0 --> B6(Telephony Manager);B0 --> B7(Resource Manager);B0 --> B8(Location manager);  end;B1 --> C(System Layer);B2 --> C;B3 --> C;B4 --> C;B5 --> C;B6 --> C;B7 --> C;B8 --> C;subgraph Kernel space;C --> C1(Display driver);C --> C2(Camera Driver);C --> C3(Flash memory);C --> C4(IPC driver);C --> C5(Keypad driver);C --> C6(Touchpad driver);C --> C7(Wifi driver);C --> C8(Audio driver);C --> C9(Power manager); end;</div>


## 4.2 컴포넌트 인터페이스

- 패키지 구성 컴포넌트 다이어그램을 명시하고 역할 내용을 기술한다

  ### Based on Android platfroms
  - MainActivity  
    - HVAC Panel Screen 구성 
    - 운전석/동승석 열선/통풍 레벨조정/Off 버튼 제공 
    - 에어컨 On/Off 버튼 제공 바람세기 조절 버튼 제공 
    - 설정온도 표시/레벨 조정 버튼 제공 
    - Sync On/Off 버튼 제공
  - Hvac panel app 기능을 지원하기 위한 객체와 인터페이스: 
    - FWUpdateManager
      - 소프트웨어 업데이트를 진행 바이너리 위치 확인 업데이트 진행 처리
    - Movable 
      - 운전석/동승석 앞/뒤/좌/우/회전  레벨 조정
    - Heatable
      - ILevelAdjustable 구현한 인터페이스 운전석/동승석 열선 Off/레벨 조정
    - ISeatBackMovable 
      - 운전석/동승석 등받이 앞/뒤 이동 레벨 조정
    - Coolable 
      - ILevelAdjustable 구현한 인터페이스 운전석/동승석 통풍 Off/레벨 조정
    - ILiftable 
      - 운전석 시트 업/다운 레벨조정
    - Temperaturable 
      - 설정온도 레벨 조정
    - ILevelAdjustable 
      - 레벨 증가/감소/Off 기능 제공 인터페이스
    - WheelLocationDown 
      - 운전대 위치 위/아래 조정
    - WheelLocationUp\
      - 운전대 위치 위/아래 조정
    - IVolumeable
      - 오디오 볼륨 조정

- mermaid를 사용하여 그린 예 (Component 예제 부족 )
<pre class="mermaid">
graph TD
A(User Action) --> B[MainActivity]
  subgraph HVAC App
    B --> C[PassengerSeat]
    B --> D[FWUpdateManager]
    B --> E[DriverSeat]
    B --> F[AirConditioner]
    B --> G[SteerWheel]
    C --> |Movable| H((Movable))
    C --> |Heatable| I((Heatable))
    C --> |ISeatBackMovable| J((ISeatBackMovable))
    C --> |Coolable| K((Coolable))
    E --> H
    E --> I
    E --> J
    E --> K
    E --> |ILiftable| L((ILiftable))
    F --> |Temperaturable| M((Temperaturable))
    F --> |ILevelAdjustable| N((ILevelAdjustable))
    G --> N
    G --> |WheelLocationDown| O((WheelLocationDown))
    G --> |WheelLocationUp| P((WheelLocationUp))
    G --> |IVolumeable| Q((IVolumeable))
  end
</pre>


## 4.3 컴포넌트 설계 명세

- 컴포넌트 각각을 구성하는 클래스/인터페이스 다이어그램을 명시하고 역할들에 대한 내용을 기술한다
- Mermaid 사용하여 그린 예 ( **[문법가이드링크](https://github.com/mermaid-js/mermaid/blob/develop/docs/classDiagram.md)** )

<pre class="mermaid">
classDiagram 
class Unit {
    <<abstract>> 
    -mX
    -mY
    -mZ
    -mRotationDegree
    +moveForward()*
    +moveBackward()*
    +moveLeft()*
    +moveRight()*
    +moveRotation()*
}
Unit <|-- Seat
Unit <|-- AirConditioner
Unit <|-- Wheel

ISeatBackMovable <|-- Seat
class ILevelAdjustable {
    <<interface>>
    +LevelUp(int value)
    +LevelDown(int value)
    +LevelOff(int value)
}
class ILiftable {
    <<interface>>
	+Up()*
	+Down()*
}
class IVolumeable {
    <<interface>>
	+VolumeUp()
	+VolumeDown()
	+VolumeMute()
}
class Seat {
    <<abstract>>
	+mHeat
	+mCool
	+mSeatBackDegree
	+moveSeatBackForward()*
	+moveSeatBackBackward()*
    +Increase(ILevelAdjustable _IFace)*
    +Decrease(ILevelAdjustable _IFace)*
    +Off(ILevelAdjustable _IFace)*
}
class Wheel {
    <<abstract>>
	-mWheelLiftLevel
	+WheelLocationUp()*
	+WheelLocationDown()*
}

class ISeatBackMovable {
    <<interface>>
    +moveSeatBackForward(int _leveldegree)*
    +moveSeatbackBackward(int _leveldegree)*
}
class MainActivity {
	+mDrvSeatHeatLevel
	+mDrvSeatCoolLevel
	+mPassengerSeatHeatLevel
	+mPassengerSeatCoolLevel
	+mAirConditionLevel
	+mAirConditionOff
	+mSyncOff
	+InitContextView()
	+RegisterEventListener9)
	+onCreate()
}
Seat <|-- DriverSeat
Seat <|-- PassengerSeat
Wheel <|-- SteerWheel

ILevelAdjustable <|.. Heatable
ILevelAdjustable <|.. Coolable
ILevelAdjustable <|-- AirConditioner
ILevelAdjustable <|-- SteerWheel 
IVolumeable <|-- SteerWheel
ILiftable  <|-- DriverSeat

MainActivity o-- DriverSeat : +mDrvHeatCoolSeat
MainActivity o-- PassengerSeat : +mPassengerHeatCoolSeat
MainActivity o-- AirConditioner : +mAirConditioner
MainActivity o-- Heatable : +mDrvHeatable 
MainActivity o-- Heatable : +mPassengerHeatable
MainActivity o-- Coolable : +mDrvCoolable
MainActivity o-- Coolable : +mPassengerCoolable
MainActivity o-- SteerWheel : +mSteerWheel
</pre>


## 4.4 워크플로우 와 알고리즘

- 기능에 따른 use case 들을 명시하고 Use Case 에 대한 처리과정에 대한 시퀀스 다이어 그램을 나타내고 간략한 설명을 기술한다
- 만일 알고리즘 관련 사항이 있다면 알고리즘 순서도도 명시하고 내용을 기술한다

### 4.4.1 Use Cases

- mermaid 를 사용하여 그린 예 ( Use Case 예제 부족 )

<pre class="mermaid">
graph LR
%% HVAC Panel Use Case example
%% 공기 환기
%% 바람세기 조정 작동
%% 운전석 통풍기능 작동
%% 운전석 열선기능 작동
%% 동승석 통풀기능 작동
%% 동승석 열선기능 작동
%% 에어컨 온오프 작동
%% 설정온도 조정 작동
%% 소프트웨어 업데이트 작동

A[User action] 
B[FrontMain] --> C[FWUpdating]
C[FWUpdating] --> B[FrontMain]
B --> D[SchdVentilation]
D --> B
B --> E[AfterBlower]
E --> B
B --> F[EngineeringMode]
F --> B

UA1(Air clean) --> B
UA2(Passenger seat cool level) --> B
UA3(Driver seat cool level) --> B
UA4(Air conditioner on off) --> B
UA5(SteerWheelHeat off 1 2 3) --> B
UA6(Passenger seat heat level 1 2 3) --> B
UA7(Driver seat heat level 1 2 3) --> B
UA8(Blower Up Down level) --> B
UA9(TempSetting level 17 to 27) --> B
UA10(Sync on off ) --> B
</pre>


### 4.4.2 Sequence Chart

4.4.2.1 Adjusting Driver Set Heat On/Off and Level Up/Down

- 운전석 열선 온/오프, 열선 레벨 1/2/3 조절 버튼 동작시 이벤트 흐름 표시
- mermaid 사용 예

<pre class="mermaid">
sequenceDiagram
%% sequence chart Adjusting Driver Seat Heat Level Up/Down and On/Off
User -->> FrontMainActivity : Press Driver seat heat button Up/Down
FrontMainActivity -->> DriverSeat : Increase(Heatable)/Decrease(Heatable)
DriverSeat -->> Heatable : Increase(Heatable)/Decrease(Heatable)
Heatable -->> Heatable : currHeat = getHeat() + _LEVEL_OFFSET / getHeat() - _LEVEL_OFFSET
    alt currHeat > HEAT_LEVEL_MAX
        Heatable --> Heatable : setHeat(HEAT_LEVEL_MAX)
    else
        alt currHeat < HEAT_LEVEL_MIN
            Heatable -->> Heatable : setHeat(HEAT_LEVEL_MIN)
        else normal processing
            Heatable -->> Heatable : setmHeat(currHeat);
        end
    end

Heatable -->> DriverSeat : return
DriverSeat -->> FrontMainActivity : return 
FrontMainActivity -->> Heatable :  getmHeat()
Heatable -->> FrontMainActivity  : return current heat level
FrontMainActivity -->> FrontMainActivity : internal processing
alt heat level == Heatable.HEAT_LEVEL_MAX
    FrontMainActivity -->> FrontMainActivity : mimageViewLeftSeatHeatOff.setImageDrawable(R.drawable.seat_status_driver_hot_03)
else
    alt heat level == Heatable.HEAT_LEVEL_MED
        FrontMainActivity -->> FrontMainActivity : mimageViewLeftSeatHeatOff.setImageDrawable(R.drawable.seat_status_driver_hot_02)
    else
        alt heat level == Heatable.HEAT_LEVEL_MIN
            FrontMainActivity -->> FrontMainActivity : mimageViewLeftSeatHeatOff.setImageDrawable(R.drawable.seat_status_driver_hot_01)
        end
    end
end

User -->> FrontMainActivity : Press Driver seat heat button Off
FrontMainActivity -->> DriverSeat : Off(Heatable)
DriverSeat -->> Heatable : LevelOff(_OFF_)
Heatable -->> DriverSeat : return
DriverSeat -->> FrontMainActivity : return
FrontMainActivity -->> FrontMainActivity : mimageViewLeftSeatHeatOff.setImageDrawable(R.drawable.seat_status_driver_off)
</pre>


4.2.2.2 Adjusting Driver Seat Cool Level toggle 1/2/3/off

- 운전석 통풍 레벨 1/2/3/off 조절 버튼 동작시 이벤트 흐름 표시


## 4.5 소프트웨어 요구사항 매핑

- SRS 항목과 어떻게 연결되는지 명시한다

| SRS 항목 | 내용 | SDD 적용  | 클래스/인터페이스 |
| --- | --- | --- | --- |
| 3.2.1.1 | 스티어링휠 열선 기능 | 전/후/좌/우/회전 속성을 정의한 Unit 추상클래스를 상속받고 휠 위치 업 다운 기능을 정의한 Wheel 추상클래스를 상속,<br> ILevelAdJustable 인터페이스를 구현한 SteerWheel객체를 통하여 열선 off/1/2 단계를 조정  할 수 있도록 적용<br> 필요시 overriding을 통하여 구현을 변경하여 적용 할 수 있음 | SteerWheel.WheelWarmUp(ILevelAdjustable obj) SteerWheel.WheelWarmDown(LevelAdjustable obj)<br>SteerWheel.LevelUp()<br>SteerWheel.LevelDown()<br>SteerWheel.LevelOff()<br>ILevelAdJustable  |
| 3.2.1.2 | 휠 위치 높이 조정 기능 | 휠 위치 업 다운 기능을 정의한 Wheel 추상클래스를 상속/구현한 SteerWheel객체를 통하여 휠 높이 조정 할 수 있도록 적용<br>필요시 overriding을 통하여 구현을 변경하여 적용 할 수 있음 | SteerWheel.WheelLocationUp() SteerWheel.WheelLocationDown() ILevelAdJustable  |
| 3.2.2.1 | 좌석 시트 열선 기능 | 좌석 전/후/좌/우/회전/업/다운/등받이 전후 이동 기능을 정의한 Seat 추상클래스를 상속/구현한 운전석/동승석DriverSeat/PassengerSeat 객체에서<br> ILevelAdjustable 인터페이스를 구현한 Heatable 객체를 사용하여 열선 On/Off,  레벨 3단계 1/2/3 조정 할수 있도록 적용 필요시 overriding을 통하여 구현을 변경하여 적용 할 수 있음 | DriverSeat.Increase(ILevelAdjustable  IFace) DriverSeat.Decrease(ILevelAdjustable IFace) DriverSeat.Off(ILevelAdjustable IFace)<br>Heatable.LevelUp()<br>Heatable.LevelDown()<br>Heatable.LevelOff() |
| 3.2.2.2 | 좌석 시트 통풍 기능 |  운전석/동승석 DriverSeat/PassengerSeat  객체에서 ILevelAdjustable 인터페이스를 구현한 Coolable 인터페이스를 사용하여 <br>통풍 On/Off,  레벨 3단계 1/2/3 조정 할수 있도록 적용 필요시 overriding을 통하여 구현을 변경하여 적용 할 수 있음 | DriverSeat.Increase(Coolable)<br>DriverSeat.Decrease(Coolable)<br>DriverSeat.Off(Coolable)<br>Coolable.LevelUp()<br>Coolable.LevelDown()<br>Coolable.LevelOff() |
| 3.2.2.3 | 전동 시프트 기능 | 좌석 전/후/좌/우/회전/등받이 전후 이동 기능과 좌석 업/다운 기능을 정의한 ILiftable 를 구현한 <br>운전석/동승석 DriverSeat/PassengerSeat 객체를 사용하여 조정 할 수 있도록 적용<br>필요시 요구사항에 맞게 overriding 하여 조정 할 수 있음 | DriverSeat.moveSeatbackForward()<br>DriverSeat.moveSeatbackBackward()<br>DriverSeat.moveForward()<br>DriverSeat.moveBackward()<br>DriverSeat.moveLeft()<br>DriverSeat.moveRight()<br>DriverSeat.moveRotation()<br>DriverSeat.Up()<br>DriverSeat.Down()<br>ILiftable  |
| 3.2.3.1 | 에어컨 On/Off | Unit 을 상속받고 ILevelAdjustable 인터페이스를 구현한 AirConditioner 객체를 통하여 에어컨을 On/Off 할 수 있도록 적용<br>필요시 overriding 하여 요구 사항에 대응 | AirConditioner.LevelOff()<br>AirConditioner.LevelUp()<br>AirConditioner.LevelDown()<br>ILevelAdjustable  |
| 3.2.3.2 | 설정 온도 |  ILevelAdjustable 인터페이스를 구현한 Temperaturable 객체를 사용하여 온도 설정을 조정 할 수 있도록 적용<br>필요시 overriding을 통하여 구현을 변경하여 적용 할 수 있음 | Temperturable.LevelUp()<br>Temperturable.LevelDown()<br>Temperturable.Off()<br> |
| 3.2.3.3 | 바람세기 |  |  |
| 3.2.3.4 | 자동건조 |  |  |
| 3.2.4.1 | 실내공기순환 |  |  |
| 3.2.4.2 | 예약 환기 |  |  |
| 3.2.5 | FWUpdate |  |  |




# 5. 인터페이스 설계 (Interface Design)

## 5.1 인터페이스 항목

- 인터페이스 항목을 리스트업 하고 입력/출력/기능등을 기술 한다