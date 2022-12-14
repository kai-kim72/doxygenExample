@page srs_example SRS Example
@tableofcontents
# SRS 

# Software Requirements Specifications of XXX

## Revision History

| Date  | Version | Descriptions | Writer |
| --- | --- | --- | --- |
| 2022,10,17 | 0.9 |  hvac srs draft version | kai.kim |
|  |  |  |  |

## Contents

# 1. 개요 (Introduction)

## 1.1 개발 목적 (Purpose)

- 

## 1.2 범위 (Scope)

- 

# 2. 참조

## 2.1 참조 문서 (Reference)

- 

## 2.2 용어 및 약어 표 (Abbreviation)

| Items | Descriptions |
| --- | --- |
| HVAC | (heating, ventilation, & air conditioning)는 난방, 환기, 냉방 즉 이들을 통합하여 실내 및 자동차 환경의 안락을 위해 쓰이는 기술 |
|  |  |

# 3. 시스템 특징 및 요구 사항 (System Feature and Requirement)

## 3.1 시스템 특징 (System Feature)

- 자동차의 난방, 환기,냉방, 좌석의 열선, 통풍, 전동 시프트, 운전대의 열선 기능 등을 제어하는  HVAC 판넬을 제공한다.
- 또한 소프트웨어의 업데이트가 필요한 경우 업데이트를 진행 할 수 있도록 기능을 제공한다

### 3.1.1 유즈 케이스 (Use Case)

- 스티어링휠 열선 레벨 조정
- 운전석/동승석 열선 레벨 조정
- 운전석/동승석 통풍 레벨 조정
- 운전석/동승석 전동 시프트 앞/뒤/좌/우/회전/업/다운 레벨 조정
- 에어컨 On/Off , 바람세기 레벨 조정, 희망 온도 설정[ Sync On(운전석/동승석 동일) , Off(운전석/동승석 각각) ]
- 실내 공기 순환 / 환기, 공기 정화, 외부 공기 유입 환기 On/Off 조정
- FWUpdating

## 3.2 기능 요구사항 (Functional Requirement)

### 3.2.1 스티어링휠

3.2.1.1 열선 기능

- 운전석 운전휠을 따듯하게  해주는 열선 기능을 제공해야 한다
- 열선 레벨은 3단계로  2/1/Off  조정 기능을 제공해야 한다

3.2.1.2 휠 위치 높이 조정 기능

- 운전석 높이에 조정에 따라 운전휠 높이도 조정 가능해야 한다
- 기본 위치에서 위쪽/아래쪽 이동이 가능해야 하며 조정 레벨은 5단계로 -2/-1/0/1/2  조정 가능해야 한다

### 3.2.2 좌석 시트

3.2.2.1 열선 기능

- 운전자/동승자 시트를 따듯하게 열선 기능을 제공해야 한다
- 열선 레벨은 3단계로  1/2/3 조정 가능하도록 제공해야 한다
- 열선 기능 Off 도 제공해야 한다.

3.2.2.2 통풍 기능

- 운전자/동승자 시트를 시원하게 하기 위하여 통풍 기능을 제공해야 한다
- 통풍 레벨은 3단계로 1/2/3 조정 가능하도록 제공해야 한다
- 통풍 기능 Off 도 제공해야 한다

3.2.2.3 전동 시프트 기능

- 운전자/동승자 시트 앞/뒤/위/아래/회전 이동 기능을 제공해야 한다
- 앞/뒤 이동 레벨은 10 단계로 5/4/3/2/1/0/-1/-2/-3/-4/-5 조정 가능하도록 제공한다
- 위/아래 이동 레벨은  6단계로 0/1/2/3/4/5 조정 가능하도록 제공한다
- 좌석 회전 이동 레벨은  좌/우 회전 방향으로 3단계 -180/-90/ 0/ 90/180 조정 가능하도록 제공한다
- 운전자/동승자 시트 등받이 앞/뒤 조정 기능을 제공해야 한다
- 등받이 앞뒤 조정 레벨은 기울어지는 각 값으로 90 ~ 0 ~ -90 으로 조절 가능 하도록 제공한다

### 3.2.3 에어컨

3.2.3.1 On/Off

- 에어컨을 끄고 켜는 기능을 제공해야 한다

3.2.3.2 설정 온도

- 온도 설정 기능을 제공해야 한다
- 온도 레벨은 섭시 표시 17 ~ 27 &deg; C  까지 이며 0.5 도씩 조정 할 수 있어야 한다
- 화시 표시 설정 온도 경우 LOW, 62℉ ∼ 82℉, HI까지 1 ℉씩 조정 표시 (온도 단위 ℉ 는 표시) 할 수 있어야 한다.  섭시/화시 변환 공식

&deg; C = ( &deg; F - 32) &times; 5/9 <br>
&deg; F = &deg; C &deg; 1.8 + 32

3.2.3.3 바람 세기

- 바람 세기 조정 기능을 제공해야 한다
- 조정 레벨은 3단계 1/2/3를 설정 할 수 있어야 한다

3.2.3.4 자동 건조

- 에어컨 자동 건조 기능을 제공해야 한다

### 3.2.4 환기 순환 정화

3.2.4.1 실내 공기 순환

- 실내 공기 순환 기능 On/Off를 제공해야 한다
- 실내 공기 정화 기능을 제공해야 한다
- 실내 공기 환기 기능을 제공해야 한다

3.2.4.2 예약 환기

- 지정한 시간에 외부 공기 유입 실내 공기 환기 기능 On/Off 를 제공해야 한다

### 3.2.5 FWUpdating

- 소프트웨어 업데이트가 필요한 경우 지원하도록 기능을 제공해야 한다
- 사용자 UI를 제시하고 업데이트 절차에 따라 사용자 동작을 받아 업데이트 작업이 진행되도록 한다 (SW 관점에서 구체적인 업데이트 절차를 기술한다)

## 3.3 성능 요구사항 (Performance Requirement)

- 

## 3.4 안전 요구사항 (Safety Requirement)

- 

## 3.5 보안 요구사항 (Security Requirement)

- 

## 3.6 인터페이스 요구사항 (Interface Requirement)

### 3.6.1 사용자 인터페이스 (User Interface)

- 

### 3.6.2 하드웨어 인터페이스 (Hardware Interface)

- 

### 3.6.3 소프트웨어 인터페이스 (Software Interface)

- 

### 3.6.4 통신 인터페이스 (Communication Interface)

- 

## 3.7 품질  요구사항 (Quality Requirement)

- 

# 4. 설계 및 구현 제약사항 (Design and Implementation constraint)

