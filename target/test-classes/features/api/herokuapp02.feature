#3- https://restful-booker.herokuapp.com/booking endpointine gerekli Query
#parametrelerini yazarak "firstname" degeri "Jim" ve "lastname" degeri
#"Jackson" olan rezervasyon oldugunu test edecek bir GET request gonderdigimizde,
#donen response'un status code'unun 200 oldugunu ve "Jim Jackson" ismine sahip
#en az bir booking oldugunu test edin.

Feature:Get_Pojo_Class
  @Bizz
  Scenario: Get sorgu
    * Herokuapp Api gerekli URL icin "booking" path param ve "John" query param hazirla
    * Herokuapp Api Get Request Expected Body hazirla
    * Herokuapp Api Get Request donen Response'i kaydet
    * Herokuapp Api Get Request donen Response'i assert et
