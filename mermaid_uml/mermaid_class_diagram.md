```mermaid
classDiagram 
%% <<
%% UML Notation Usage:
%% add * to Abstract e.g.: someAbstractMethod()*
%% add $ Static e.g.: someStaticMethod()$
%% add + Public
%% add - private
%% add # Protected
%% add ~ Package/Internal
%% classA --|> classB : Inheritance
%% classC --* classD : Composition
%% classE --o classF : Aggregation
%% classG --> classH : Association
%% classI -- classJ : Link(Solid)
%% classK ..> classL : Dependency
%% classM ..|> classN : Realization
%% classO .. classP : Link(Dashed)
%% >>
class Unit
<<abstrct>> Unit
Unit : -mX
Unit : -mY
Unit : -mZ
Unit : -mRotationDegree
Unit : +moveForward()*
Unit : +moveBackward()*
Unit : +moveLeft()*
Unit : +moveRight()*
Unit : +moveRotation()*

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

```

```mermaid
graph TD
%% HVAC APP Package diagram example
%% HVAC Panel Screen 구성
%%  운전석/동승석 열선/통풍 레벨조정/Off 버튼 제공
%%  에어컨 On/Off 버튼 제공    
%%  바람세기 조절 버튼 제공
%%  설정온도 표시/레벨 조정 버튼 제공
%%  Sync On/Off 버튼 제공 
A(User Action) --> B[MainActivity<br> <br> HVAC Panel Screen 구성<br>운전석/동승석 열선/통풍 레벨조정/Off 버튼 제공<br>에어컨 On/Off 버튼 제공<br>바람세기 조절 버튼 제공<br>설정온도 표시/레벨 조정 버튼 제공<br>Sync On/Off 버튼 제공]
    subgraph HVAC App
    B --> C[PassengerSeat]
    B --> D[SyncManager<br><br>소프트웨어 업데이트를 진행<br>바이너리 위치 확인<br>업데이트 진행 처리]
    B --> E[DriverSeat]
    B --> F[AirConditioner]
    B --> G[SteerWheel]
    
    C --- |Movable| H((Movable<br><br>운전석/동승석 <br>앞/뒤/좌/우/회전 <br> 레벨 조정)) 
    C --> |Heatable| I((Heatable<br><br>ILevelAdjustable<br>구현한 인터페이스<br>운전석/동승석 열선<br>Off/레벨 조정))
    C --> |ISeatBackMovable| J((ISeatBackMovable<br><br>운전석/동승석 <br>등받이 앞/뒤 이동<br>레벨 조정))
    C --> |Coolable| K((Coolable<br><br>ILevelAdjustable<br>구현한 인터페이스<br>운전석/동승석 통풍<br>Off/레벨 조정))

    E --> H
    E --> I
    E --> J
    E --> K
    E --> |ILiftable| L((ILiftable<br><br>운전석 시트<br>업/다운<br>레벨조정))

    F --> |Temperaturable| M((Temperaturable<br><br>설정온도<br>레벨 조정))
    F --> |ILevelAdjustable| N((ILevelAdjustable<br><br>레벨 증가/감소/Off<br>기능 제공<br>인터페이스))

    G --> N
    G --> |WheelLocationDown| O((WheelLocationDown<br><br>운전대 위치<br>위/아래 조정))
    G --> |WheelLocationUp| P((WheelLocationUp<br><br>운전대 위치<br>위/아래 조정))
    G --> |IVolumeable| Q((IVolumeable<br><br>오디오 <br>볼륨 조정))
    end

```


```plantuml
@startuml
package "HVAC App" {

    interface "Movable" as IMovable
    note bottom
    운전석/동승석 
    앞/뒤/좌/우/회전 
    레벨 조정
    end note
    
    interface "User Action" as Event
    Event --> [MainActivity]
    note right
    HVAC Panel Screen 구성
    운전석/동승석 열선/통풍 레벨조정/Off 버튼 제공
    에어컨 On/Off 버튼 제공    
    바람세기 조절 버튼 제공
    설정온도 표시/레벨 조정 버튼 제공
    Sync On/Off 버튼 제공
    end note
    [MainActivity] --> [DriverSeat]
    [MainActivity] --> [PassengerSeat]
    [MainActivity] --> [AirConditioner]
    [MainActivity] --> [SteerWheel]
    [MainActivity] --> [SyncManager]
    note right
    소프트웨어 업데이트를 진행
    바이너리 위치 확인
    업데이트 진행 처리
    end note

    [DriverSeat] --> ILiftable
    [DriverSeat] --> ISeatBackMovable
    [DriverSeat] --> Heatable
    [DriverSeat] --> Coolable
    [DriverSeat] -- IMovable

    [PassengerSeat] --> ISeatBackMovable
    [PassengerSeat] --> Heatable
    [PassengerSeat] --> Coolable
    [PassengerSeat] -- IMovable

    [AirConditioner] --> ILevelAdjustable
    [AirConditioner] --> Temperaturable

    [SteerWheel] --> IVolumeable
    [SteerWheel] --> WheelLocationUp
    [SteerWheel] --> WheelLocationDown
    [SteerWheel] --> ILevelAdjustable
  
  note bottom of Coolable
    ILevelAdjustable 
    구현한 인터페이스
    운전석/동승석 
    통풍 Off/레벨 조정 
  end note

  note bottom of Heatable
    ILevelAdjustable 
    구현한 인터페이스
    운전석/동승석 
    열선 Off/레벨 조정 
  end note

  note bottom of ISeatBackMovable
    운전석/동승석 
    등받이 앞/뒤 
    이동 레벨 조정
  end note

  note bottom of ILiftable
    운전석 시트 
    업/다운 
    레벨 조정
  end note

  note bottom of Temperaturable
    설정온도 
    레벨 조정
  end note

  note bottom of ILevelAdjustable
    레벨 증가/감소/Off 
    기능 제공 인터페이스
  end note
    
  note bottom of WheelLocationDown
    운전대 위치
    위/아래 조정
  end note

  note bottom of WheelLocationUp
    운전대 위치
    위/아래 조정
  end note

  note bottom of IVolumeable
    오디오 
    볼륨 조정
  end note
}


@enduml
```

```plantuml
@startuml
skinparam classAttributeIconSize 0
abstract class "Unit"
Unit : -mX
Unit : -mY
Unit : -mZ
Unit : -mRotationDegree
Unit : +moveForward()*
Unit : +moveBackward()*
Unit : +moveLeft()*
Unit : +moveRight()*
Unit : +moveRotation()*

Unit <|-- Seat
Unit <|-- AirConditioner
Unit <|-- Wheel

ISeatBackMovable <|-- Seat


abstract class Wheel {
	-mWheelLiftLevel
	+WheelLocationUp()*
	+WheelLocationDown()*
}

abstract class Seat {
	+mHeat
	+mCool
	+mSeatBackDegree
	+moveSeatBackForward()*
	+moveSeatBackBackward()*
    +Increase(ILevelAdjustable _IFace)*
    +Decrease(ILevelAdjustable _IFace)*
    +Off(ILevelAdjustable _IFace)*
}

interface ISeatBackMovable {
    +moveSeatBackForward(int _leveldegree)*
    +moveSeatbackBackward(int _leveldegree)*
}

interface ILevelAdjustable {
    +LevelUp(int value)
    +LevelDown(int value)
    +LevelOff(int value)
}

interface ILiftable {
	+Up()*
	+Down()*
}

interface IVolumeable {
	+VolumeUp()
	+VolumeDown()
	+VolumeMute()
}



class DriverSeat {
    -mSeatLiftingLocation
    +DriverSeat()
    +moveSeatBackForward()
    +moveSeatBackBackward()
    +Increase()
    +Decrease()
    +Off()
    +Up()
    +DOwn()
}

class PassengerSeat {
    +PassengerSeat()
    +moveSeatBackForward()
    +moveSeatBackBackward()
    +Increase()
    +Decrease()
    +Off()
}

class AirConditioner {
    -mACLevel
    +AirConditioner()
    +moveForward()
    +moveBackWard()
    +moveLeft()
    +moveRight()
    +moveRotation()
    +LevelUp()
    +LevelDown()
    +LevelOff()
}

class Heatable {
    -mHeat
    +Heatable()
    +LevelUp()
    +LevelDown()
    +LevelOff()
}

class Coolable {
    -mCool
    +Coolable()
    +LevelUp()
    +LevelDown()
    +LevelOff()
}

class SteerWheel {
    -mWheelWarm
    -mVolumeLevel
    +SteerWheel()
    +moveForward()
    +moveBackWard()
    +moveLeft()
    +moveRight()
    +moveRotation()
    +WheelLocationUp()
    +WheelLocationDown()
    +VolumeUp()
    +VolumeDown()
    +VolumeMute()
    +LevelUp()
    +LevelDown()
    +LevelOff()
    +WheelWarmUp()
    +WheelWarmDown()
    +WheelWarmOff()
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

note right of Unit
    abstract class
    객체의 위치(mX,mY,mZ,mRotationDegree)
    전/후/좌/우/회전 이동 기능 정의
end note

note right of Seat
    abstract class
    unit 속성 상속
    좌석 등받이 전/후 이동 기능 정의
    열선/통풍 레벨 증가/감소/Off 조정 기능 정의
end note

note bottom of DriverSeat
    운전석 등받이 전/후 조정 기능 구현
    열선/통풍 Off/레벨조정 기능 구현
    운전석 시트 Up/Down 조정 기능 구현
    운전석 시트 전/후/좌/우/회전 기능 구현
end note

note bottom of PassengerSeat
    동승석 시트 전/후/좌/우/회전 기능 구현
    동승석 등받이 전/후 이동 기능 구현
    동승석 열선/통풍 Off/레벨조정 기능 구현
end note

note bottom of AirConditioner
    에어컨 On/Off 기능 구현
    바람세기 레벨 조정 기능 구현    
end note

note bottom of Wheel
    abstract class
    Unit 속성 상속
    휠 위치 위/아래 이동 기능 정의
end note

note bottom of SteerWheel
    운전대 열선 Off/레벨 조정 기능 구현
    운전대 위치 위/아래 이동 기능 구현
    오디오 볼륨 Mute/레벨 조정 기능 구현
end note

note bottom of Heatable
    열선 Off/레벨 증가/감소 
    조정 정의 인터페이스 구현
end note

note bottom of Coolable
    통풍 Off/레벨 증가/감소
    조정 기능 정의 인터페이스 구현
end note

note right of ISeatBackMovable
    좌석 등받이 전/후 이동 기능
    정의 인터페이스
end note

note bottom of ILiftable
    좌석 위치 올림/내림 이동 기능 
    정의 인터페이스
end note

note left of ILevelAdjustable
    특정 기능 레벨 값 증가/감소/Off 
    조정 기능 정의 인터페이스
end note

note left of IVolumeable
    오디오 볼륨 
    Mute/레벨조정 기능 
    정의 인터페이스
end note

note top of MainActivity
   HVAC Panel Screen 구성
    운전석/동승석 열선/통풍 레벨조정/Off 버튼 제공
    에어컨 On/Off 버튼 제공    
    바람세기 조절 버튼 제공
    설정온도 표시/레벨 조정 버튼 제공
    Sync On/Off 버튼 제공
end note

@enduml
```

```plantuml
@startuml

title 
HVAC Panel Use Case example

end title

left to right direction
actor "User" as usr
rectangle HVAC_Panel {
    usecase "EngineeringMode" as UC1
    usecase "AfterBlower" as UC2
    usecase "SchdVentilation" as UC3    
    usecase "FWUpdating" as UC4
    usecase "FrontMainActivity" as UC5   
}

usr --> UC5 : Air clean
usr --> UC5 : passenger seat cool level 1/2/3/off
usr --> UC5 : driver seat cool level 1/2/3/off
usr --> UC5 : air conditioner on/off
usr --> UC5 : SteeringWheelHeat off/1/2
usr --> UC5 : Passenger seat Heat level 1/2/3
usr --> UC5 : Driver seat Heat level 1/2/3
usr --> UC5 : BlowerUp/Down/level
usr --> UC5 : TempSetting level 17 ~ 27
usr --> UC5 : Sync On/Off

UC5 <--> UC1
UC5 <--> UC2
UC5 <--> UC3
UC5 <--> UC4

@enduml
```

```mermaid
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


```

```mermaid
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

```


```plantuml
@startuml
title sequence chart Adjusting Driver Seat Heat Level Up/Down and On/Off
actor User

User --> FrontMainActivity : Press Driver seat heat button Up/Down
FrontMainActivity --> DriverSeat : Increase(Heatable)/Decrease(Heatable)
DriverSeat --> Heatable : LevelUp(_LEVEL_OFFSET)/LevelDown(_LEVEL_OFFSET)
Heatable --> Heatable : currHeat = getHeat() + _LEVEL_OFFSET / getHeat() - _LEVEL_OFFSET
alt currHeat > HEAT_LEVEL_MAX
    Heatable --> Heatable : setHeat(HEAT_LEVEL_MAX)
else
    alt currHeat < HEAT_LEVEL_MIN
        Heatable --> Heatable : setHeat(HEAT_LEVEL_MIN)
    else normal processing
        Heatable --> Heatable : setmHeat(currHeat);
    end
end
Heatable --> DriverSeat
DriverSeat --> FrontMainActivity 
FrontMainActivity --> Heatable :  getmHeat()
Heatable --> FrontMainActivity  : return current heat level
FrontMainActivity --> FrontMainActivity 
alt heat level == Heatable.HEAT_LEVEL_MAX
    FrontMainActivity --> FrontMainActivity : mimageViewLeftSeatHeatOff.setImageDrawable(R.drawable.seat_status_driver_hot_03)
else
    alt heat level == Heatable.HEAT_LEVEL_MED
        FrontMainActivity --> FrontMainActivity : mimageViewLeftSeatHeatOff.setImageDrawable(R.drawable.seat_status_driver_hot_02)
    else
        alt heat level == Heatable.HEAT_LEVEL_MIN
            FrontMainActivity --> FrontMainActivity : mimageViewLeftSeatHeatOff.setImageDrawable(R.drawable.seat_status_driver_hot_01)
        end
    end
end

User --> FrontMainActivity : Press Driver seat heat button Off
FrontMainActivity --> DriverSeat : Off(Heatable)
DriverSeat --> Heatable : LevelOff(_OFF_)
Heatable --> DriverSeat
DriverSeat --> FrontMainActivity
FrontMainActivity --> FrontMainActivity : mimageViewLeftSeatHeatOff.setImageDrawable(R.drawable.seat_status_driver_off)

@enduml
```

```plantuml
@startuml
title sequence chart Driver Seat Cool Level toggle as like 1/2/3/off
actor User

User --> FrontMainActivity : Press Driver seat cool button 
FrontMainActivity --> DriverSeat : Increase(Coolable)
DriverSeat --> Coolable : LevelUp(_LEVEL_OFFSET)
Coolable --> Coolable : currCool = getCool() + _LEVEL_OFFSET 
alt currCool > COOL_LEVEL_MAX
    Coolable --> Coolable : setmCool(COOL_OFF)
else
    alt currCool <= COOL_OFF
        Coolable --> Coolable : setmCool(COOL_LEVEL_MIN)
    else normal processing
        Coolable --> Coolable : setmCool(currCool);
    end
end
Coolable --> DriverSeat
DriverSeat --> FrontMainActivity 
FrontMainActivity --> Coolable :  getmCool()
Coolable --> FrontMainActivity  : return current cool level
FrontMainActivity --> FrontMainActivity 
alt heat level == Coolable.HEAT_LEVEL_MAX
    FrontMainActivity --> FrontMainActivity : mimageViewLeftSeatCool.setImageDrawable(R.drawable.seat_status_driver_cool_03)
else
    alt heat level == Coolable.HEAT_LEVEL_MED
        FrontMainActivity --> FrontMainActivity : mimageViewLeftSeatCool.setImageDrawable(R.drawable.seat_status_driver_cool_02)
    else
        alt heat level == Coolable.HEAT_LEVEL_MIN
            FrontMainActivity --> FrontMainActivity : mimageViewLeftSeatCool.setImageDrawable(R.drawable.seat_status_driver_cool_01)
        else
            FrontMainActivity --> FrontMainActivity : mimageViewLeftSeatCool.setImageDrawable(R.drawable.seat_status_driver_off)    
        end
    end
end

@enduml
```