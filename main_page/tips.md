/*! @page tips_page Doxygen Tips
* @tableofcontents
* \n
* @section noted_sec 사용자에게 알릴 메시지 삽입 예시
* @note 참고 설명
* @todo 할 일 설명
* @pre 미리 호출해야 할 사항
* @bug 버그 설명
* @warning 참고 링크, 페이지
* @see 참고할 함수 또는 페이지
* 
* \n
* @section link_sec 링크 삽입 예시
* 다음과 같이 링크를 삽입할 수 있습니다.
* [Litbig](http://www.litbig.com) 에 오시면 Litbig에 관련된 많은 정보를 보실 수 있습니다.\n
* @note 자주 방문해주세요 :)
* 
* \n
* @section ref_sec 클래스, 함수 이름 연동
* 다음과 같이 클래스 이름 또는 함수 연동할 수 있습니다.\n
* Property::Property \n
* 또는 다음과 같이도 가능합니다. \n
* \ref Property::Property "Property"
* 
* \n
* @section table_sec 표 삽입 예시
* 표는 다음과 같이 삽입합니다.
*	항목								|				설명
*	--------------------------------|---------------------------------
*	\ref VehicleIf::setSystemProperty "setSystemProperty"	|	VehicleIf systemproperty 설정 함수
*	\ref Property::systemPropertyChanged "systemPropertyChanged"		|	systemproperty 변경시 불리는 콜백
* \n
* @section image_sec 이미지 삽입 예시
* 이미지는 다음과 같이 삽입합니다.
* @image html litbig-logo-50.png "캡션 삽입"\n
* \n
* @section quote_sec 인용 문구 삽입 예시
* 인용 문구는 다음과 같이 삽입합니다.
* > Litbig Doxygen입니다.\n
* > 이렇게 여러 줄 삽입이 가능합니다.
* \n
* @section code_sec 코드 삽입 예시
* 코드는 다음과 같이 삽입합니다. 자동으로 클래스 및 함수가 링크됩니다.
* @code{.cpp}
* int main(int argc, char *argv[]){
*   ...
*
*   QQmlApplicationEngine engine;
*   Property property(&engine);
*   VehicleIf vehicle(&engine);
*   ...
* }
* @endcode
* \n
* 
* @section procedure_sec Step by step 절차 예시
* 어떤 절차에 대한 순서를 설명하는 경우가 있습니다.\n
* 아래와 같이 Section 링크를 연결하여 설명을 작성할 수 있습니다.\n
* @ref step1 과 @ref step2 를 주의 깊게 진행하세요.
* 
* @subsection step1 Step 1
* 1단계에 대한 설명.
* @subsection step2 Step 2
* 2단계에 대한 설명.
* \n
* \n
*
* @section Latex_Notation Latex Formula Example
* ### 블록입력방식
* $$ \lim_{x \to 2} 3x = 6 $$
*
* ### 각종 기호
* &alpha; &beta; &gamma; &delta; &epsilon; &zeta;
* &eta; &theta; &iota; &kappa; &lambda; &mu; &nu;
* &xi; &omicron; &pi; &rho; &sigma; &sigmaf;
* &tau; &upsilon; &phi; &chi; &psi; &omega;
*
* &Gamma; &Delta; &Theta; &Lambda; &Xi; &Pi;
* &Sigma; &Phi; &Psi; &Omega;
*
* &int; &sum; &prod; &minus; &plusmn; &infin;
* &asymp; &prop; = &equiv; &ne; &le; &ge;
* &times; &middot; &sdot; &divide; &part; &prime; &Prime;
* &nabla; &permil; &deg; &there4; &empty;
*
* &isin; &notin; &cap; &cup; &sub; &sup; &sube; &supe;
* &not; &and; &or; &exist; &forall;
* &rArr; &hArr; &rarr; &harr; &uarr; &darr;
* &alefsym; - &ndash; &mdash;
*
* $$ \le, \geq, \neq, \infty, \alpha, \beta, \pi, \in, \ni $$
* $$ \alpha \beta \gamma \delta \epsilon \zeta \eta \theta $$
* $$ \iota \kappa \lambda \mu \nu \xi \omicron \pi $$
* $$ \rho \sigma \tau \upsilon \phi \chi \psi \omega $$
*
*
* ### 사칙연산
* #### $$ + - \times \div $$ 입력시
* $$ + - \times \div $$
*
* ### 루트
* $$ \sqrt{x}, \sqrt[3]{y} $$
*
* ### 지수, 로그 삼각 함수
* $$ a^{x}, \log_{b}{y} $$
* $$ \sin{x}, \cos{x}, \tan{x} $$
*
* ### 분수식
* $$ \dfrac{분자}{분모} $$
*
* $$ 2 = \left( \frac{\left(3-x\right) \times 2}{3-x} \right) $$
*
* ### 벡터
* $$ \vec{a}, \overrightarrow{AB} $$
*
* ### 극한, 시그마, 적분
* $$ \lim_{x \to a} f(x) $$
* $$ \sum_{n = 0}^{N} g(n) $$
* $$ \int_{a}^{b} h(x) dx $$
* $$ \iiint_{E} f(x,y,z) dV $$
* $$ \oint_{S} P dx $$
* $$ \lim_{n \to \infty} \sum_{k = 1}^{n} \dfrac{1}{k} $$
* $$ \oint_{C} \vec{F} \cdot d\vec{s} $$
* $$ \int_e^{\infty}\frac {1}{t(\ln t)^2}dt = \left. \frac{-1}{\ln t} \right\vert_e^\infty = 1 $$
* $$ \sum_{m=1}^\infty\sum_{n=1}^\infty\frac{m^2 n}{3^m\left(m 3^n + n 3^m\right)} $$
*
* $$ \phi_n(\kappa) =
* \frac{1}{4\pi^2\kappa^2} \int_0^\infty
* \frac{\sin(\kappa R)}{\kappa R}
* \frac{\partial}{\partial R}
* \left [ R^2\frac{\partial D_n(R)}{\partial R} \right ] \,dR $$
*
* ### 수식묶음, 줄맛춤, 행렬
* - \begin{} 과 \end{} 를 이용하며 \두 개 혹은 세개로 줄을 띄운다.
* 줄맞춤의 경우에는 & 기호를 기준으로 정렬된다.
*
* $$ \begin{cases} 식1 \\\ 식2 \\\ 식3 \end{cases} $$
*
* $$ \begin{align} f(x) = &x^2 - 2x + 1 \\\ = &(x-1)^{2} \end{align} $$
* $$ \begin{bmatrix} 1 & 2 \\\ 3 & 1 \\\ 5 & -3 \end{bmatrix} $$
*
*
* ### 태그 달기
* $$ y = (x-1)^{2} \tag{식1} $$
*
* ### 수식 내어 쓰기
* - 일반적으로 수식 내부에서 스페이스바로는 띄어 쓰기를 할 수 없다.<br>
*  따라서 다음과 같은 코드로 띄어쓰기를 한다.
*
*  약간 띄어 쓰기 $$ \; $$
*
*  조금 길게 띄어 쓰기 $$ \quad $$
*
* $$ \int_{a}^{b} \cos(\theta)\;\sin(\theta)\; d\theta $$
*
*
* ### 색깔 넣어 표기
*
* $$ x=\frac{{\color{Blue}-b}\pm\sqrt{\color{Red}b^2-4ac}}{\color{Green}2a} $$
*
* $$ \frac{-b\color{Green}\pm\sqrt{b^2\color{Blue}-4{\color{Red}a}c}}{2a}=x $$
*
*/