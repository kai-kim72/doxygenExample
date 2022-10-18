@mainpage IK-PE2 Hvac Panel Software
<!-- @tableofcontents  -->
 # Contents
 
 - @ref Version_info
 - @ref SW_Requirements
 - @ref system_architecture
 - @subpage SW_Package
 - @ref SW_Architecture
 - @subpage srs_example
 - @subpage sdd_example
 - @subpage tips_page
 
==========================================================================================================
 

 @section first_sec Descriptions

  - HVAC Panel에 대한 개략적인 설명을 기술한다

   <img src="panel_01.png" alt="panel_main" />

    ### Supported Control Itmes
	  - Air Condition
	  - Air Clean
	  - Scheduled Ventilation
	  - Driver/Passenger Seat Heat/Cool
	  - Driver/Passenger Ventilation
	  - Driver/Passenger Floor
	  - SteeringWheelHeat
	  - Driver/Passenger Temperature Level
	  - Blower Up/Down level
	  - Theme
	  - LCD Brightness level
	  - Haptic Strength level

	
----------------------------------------------------------------------------------------------------------	

@section second_sec Component Information

- 각각의 컴포넌트에 대한 설명을 기술한다
  
  <img src="hvacpkgCompDescription01.png" alt="hvac_panel_pkg_components" />

 ## QML Group (Main Process)
  - QGUIApplication based on QTcore and QTQMLframework is executed in main()
	- Showing Main Screen based on FrontMain/Schdventilation/EngineeringMode/AfterBlower.qml
	- If user select or adjusting an option as shown use cases on above figure<br>
    , then the generated signal will be delivered to Micom & CPU thru Pandel Group
	
 ## Update Group(FWUpdateManager, FWUpdateThread)
	- If user select Sync option on FrontMain screen then communication between Main Thread and FWUpdateThread is started
	- Listening a request event sent from Main Thread and proceeding Update request thru FWUpdateThread
	- Checks UpdatingReady and Mainstaus &Snor
	- Notify the result of Updating to FWUpdateManager
	
 ## Pandel Group(Property/VehicleIf class) 
  - Provide signals and slots for changing systemProperty and send the event to MicomGroup
	- Property API can access system property for playing haptic and set or update haptic strength/LcdBrightness/LcdPower 
	- VehicleIf API can access Haptic Touch IC for getting status and reseting the IC

 ## Micom Group(MicomIf::txThread/rxThread)
	- Listening signals and composite MicomMsg for (can0 /vehicle / ipcdata), and sending the Msg to CPU
  
	
============================================================================================================


  ## Contributors
  
  -----
  
  ### SW 
    - **개발자**
		
	### HW
	
	
	### CEO
	
	
  ------

  
  ## Contact Us
    - **주)리트빅**  경기도 성남시 분당구 판교로 228번길 17, 6층 601호 (삼평동, 세븐밴처밸리2 이렌택동) <br>
	     **[www.litbig.com](http://www.litbig.com)**    
       Phone. 082-31-698-2181  /  AS Center. 070-4035-4491  /  Fax. 082-31-698-2182
 
       Copyright © Litbig. All rights reserved.
	
===================================


 @page Version_info Version Info
 ## IK-PE2 Hvac Panel Software Documents Version 0.0.01
	- based on software version **hp_fw_v0.0.01** (Release Date: 2022-08-19)
	
	
 ----------------------------------------------------------------------------------------------------------	


@page SW_Requirements Software Requirements
- 소프트웨어 요구사항들을 명시한다
  
  ## IK-PE2 Hvac Panel Software Requirement
 
  -----

  - <img src="Req_architecture_diagram04.png" alt="Requirement04" />



-

----------------------------------------------------------------------------------------------------------		
	
@page system_architecture System Architecture
  ## Physical Architecture Overview
  - Describe the hardware components on which software runs and their interactions/relationships,<br>
    Use components diagrams, deployment diagrams, network diagrams, interface diagrams…

  ### Hardware Component Description
  -  Describe the content of each hardware component in the architecture
     Optional, you may not do it if your software is not class C according to IEC 62304
     The description shall contain:
     Its identification
     The purpose of the component
     The software component it receives
     Its technical characteristics: type of machine, CPU, RAM, disk and so on.
     Its network hardware interfaces

  - 시스템 블럭도를 명시하고 시스템에 대한 간략한 설명과 SW가 따라야 할 시스템 요구 사항등을 기술한다
 
  ### System Block Diagram
  <img src="tcc8034_block_diagram01.png" alt="system_diagram" />

  #### System environment
  - 텔레칩스 TCC8034 Application Process and Interface 구조


  #### External Interface
  - 시스템상의 HW, SW 인터페이스를 기술한다


  #### Hardware interface
  - For PEMS/Electro-medical Devices, add requirements about integration of software and hardware.

  #### Network interface
  -  add here communication and networks stuff, like IP, wireless, Bluetooth …
  
----------------------------------------------------------------------------------------------------------	


  ## Logical Architecture Overview
  - Describe the top level software components and their interactions/relationships.
    Use UML package diagrams and/or layer diagrams and/or interface diagrams.
    Describe also the operating systems on which the software runs.

    The description should contain:
    Its identification
    The purpose of the component,
    Its interfaces with other components,
    Its network interfaces,
    The hardware resources it uses, for example: average RAM usage, peak RAM usage and <br>
    peak frequency and duration, disk space for permanent data, disk space for cache data, <br>
    average CPU usage, peak CPU usage and peak frequency and duration …
    
    ---
    - @ref SW_Architecture 


----------------------------------------------------------------------------------------------------------	

 @page SW_Package Software Package

  ## IK-PE2 Hvac Panel Package Diagram

  - 패키지를 구성하는 컴포넌트에 대한 설명을 기술한다  
  
  - <img src="package_diagram01.png" alt="package diagram" />
  
  -----
  
 ## QML Group (Main Process)
  - QGUIApplication based on QTcore and QTQMLframework is executed in main()
	- Init VehicleIf and Property and FWUpdateManager
	- Load and update systemProperty by using Property::systemPropertyChanged for setLcdPower,LcdBrightness,HapticStrength
	- Register Metatype PropData
	- Connect QObject; vehicleIf to systemPropertyChanged and Property to systemProperty
	- Load main.qml thru QQmlApplicationEngine.load("qrc:/qml/main.qml")
	- Run QT Application process by using QGuiApplication.exec()
	- Play Haptic thru Property and Read TouchIc Status , reset Touch IC thru vehicleIf
	
 ## Update Group(FWUpdateManager, FWUpdateThread)
	- Communicates Main Thread and FWUpdateThread
	- Listening a request event sent from Main Thread and proceeding Update request thru FWUpdateThread
	- Checks UpdatingReady and Mainstaus &Snor
	- Notify the result of Updating to FWUpdateManager
	
 ## Pandel Group(Property/VehicleIf class) 
  - Provide signals and slots for changing systemProperty and send the event to MicomGroup
	- Property API can access system property for playing haptic and set or update haptic strength/LcdBrightness/LcdPower 
	- VehicleIf API can access Haptic Touch IC for getting status and reseting the IC

 ## Micom Group(MicomIf::txThread/rxThread)
	- Listening signals and composite MicomMsg for (can0 /vehicle / ipcdata), and sending the Msg to CPU
	
----------------------------------------------------------------------------------------------------------	
	

@page SW_Architecture Software Architecture	and Design Description
 - 소프트웨어 아키텍쳐 다이어 그램과 구성된 모듈 컴포넌트 클래스/인터페이스/사용자 Use case 등을 명시한다
    
   
  ## IK-PE2 Hvac Panel Applications Architecture

  -----
  
  - <img src="architecture_diagram01.png" alt="Architecture01" />
  - <img src="architecture_diagram02.png" alt="Architecture02" />
  - <img src="architecture_diagram03.png" alt="Architecture03" />
  
  - @ref SW_Package

  - **HVAC Application based on Android Platforms**
<div class="mermaid">
graph TD; subgraph User Space; A(Applications) --> A5(HVAC App);A --> A1[home];A --> A2(Contacts);A --> A3(Phone);A --> A4(browser);A5 --> A51(DriverSeat);A5 --> A52(PassengerSeat);A5 --> A53(SteerWheel);A5 --> A54(AirConditioner);A5 --> A56(FWUpdatemanager);A5 --> A57(ILiftable);A5 --> A58(IVolumeable);A5 --> A59(ILevelAdjustable);A5 --> A510(Heatable);A5 --> A511(Coolable);  end;A1 --> B0(Application Framework);A2 --> B0;A3 --> B0;A4 --> B0;A51 --> B0;A52 --> B0; A53 --> B0;A54 --> B0;A56 --> B0;A57 --> B0;A58 --> B0;A59 --> B0;A510 --> B0;A511 --> B0;subgraph Applcation Framework;B0 --> B1(ActivityManager);B0 --> B2(Window Manager);B0 --> B3(Content Provider);B0 --> B4(View System);B0 --> B5(Package Manager);B0 --> B6(Telephony Manager);B0 --> B7(Resource Manager);B0 --> B8(Location manager);  end;B1 --> C(System Layer);B2 --> C;B3 --> C;B4 --> C;B5 --> C;B6 --> C;B7 --> C;B8 --> C;subgraph Kernel space;C --> C1(Display driver);C --> C2(Camera Driver);C --> C3(Flash memory);C --> C4(IPC driver);C --> C5(Keypad driver);C --> C6(Touchpad driver);C --> C7(Wifi driver);C --> C8(Audio driver);C --> C9(Power manager); end;</div>

-----

  ## IK-PE2 Hvac Panel Component Diagram

  - 패키지 구성 컴포넌드 다이어 그램을 명시하고 역할 내용을 기술한다 
  
  - **Based on Android platfroms**
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


  - **Based on Linux QT**
  - <img src="component_diagram01.png" alt="component diagram1" />

  - 
 -----

  ## IK-PE2 Hvac Panel Class Diagram
  - 컴포넌트 각각을 구성하는 클래스/인터페이스 다이어 그램을 명시하고\n
    역할들에 대한 내용을 기술한다  
  - **Based on Android Platforms**
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

  - **Based on Linux QT**
  - <img src="class_diagram01-page1.png" alt="class diagram1" />
  - <img src="class_diagram01-page2.png" alt="class diagram2" />
  - <img src="class_diagram01-page3.png" alt="class diagram3" />
  
  -----

  ## IK-PE2 Hvac Panel Use Case Diagram
  - 기능에 따른 use case 들을 명시한다

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

  - <img src="usecase_diagram01.png" alt="use case diagram" />
  
  -----

  ## IK-PE2 Hvac Panel Init and Workflow and Algorithms Sequence Diagram
  - Use Case 에 대한 처리과정에 대한 시퀀스 다이어 그램을 명시하고 간략한 설명을 기술한다
  - 만일 알고리즘 관련 사항이 있다면 알고리즘 순서도도 명시하고 내용을 기술한다
    
    - <img src="sequence_diagram02.png" alt="Init" />
  <br>
  <br>
    -  **sequence chart Adjusting Driver Seat Heat Level Up/Down and On/Off**
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

  
-----------------------------------------------------------------------------------------------------------

