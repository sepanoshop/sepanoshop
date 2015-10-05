<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://tempuri.org/" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://tempuri.org/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://tempuri.org/">
      <s:element name="SendMessage">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Username" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Passwod" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SenderNumebr" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="MessageBody" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ReciptionNumbers" type="tns:ArrayOfString" />
            <s:element minOccurs="1" maxOccurs="1" name="Class" type="s:int" />
            <s:element minOccurs="0" maxOccurs="1" name="UserMessageId" type="tns:ArrayOfLong" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ArrayOfString">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="string" nillable="true" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfLong">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="long" type="s:long" />
        </s:sequence>
      </s:complexType>
      <s:element name="SendMessageResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SendMessageResult" type="tns:ArrayOfLong" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetMessageStatus">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Username" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Passwod" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SenderNumebr" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="MessageId" type="tns:ArrayOfLong" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetMessageStatusResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetMessageStatusResult" type="tns:ArrayOfInt" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ArrayOfInt">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="int" type="s:int" />
        </s:sequence>
      </s:complexType>
      <s:element name="SendSchaduleMessage">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Username" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Passwod" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SenderNumebr" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="MessageBody" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ReciptionNumbers" type="tns:ArrayOfString" />
            <s:element minOccurs="1" maxOccurs="1" name="SendDate" type="s:dateTime" />
            <s:element minOccurs="1" maxOccurs="1" name="Class" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendSchaduleMessageResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SendSchaduleMessageResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendCourseMessage">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Username" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Passwod" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SenderNumebr" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Topic" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="MessageBody" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ReciptionNumbers" type="tns:ArrayOfString" />
            <s:element minOccurs="1" maxOccurs="1" name="StartDate" type="s:dateTime" />
            <s:element minOccurs="1" maxOccurs="1" name="EndDate" type="s:dateTime" />
            <s:element minOccurs="1" maxOccurs="1" name="Period" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="Class" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SendCourseMessageResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SendCourseMessageResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetCredit">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Username" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SenderNumber" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetCreditResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="GetCreditResult" type="s:long" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetReciveMessage">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Username" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Password" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="SenderNumber" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetReciveMessageResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetReciveMessageResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="SendMessageSoapIn">
    <wsdl:part name="parameters" element="tns:SendMessage" />
  </wsdl:message>
  <wsdl:message name="SendMessageSoapOut">
    <wsdl:part name="parameters" element="tns:SendMessageResponse" />
  </wsdl:message>
  <wsdl:message name="GetMessageStatusSoapIn">
    <wsdl:part name="parameters" element="tns:GetMessageStatus" />
  </wsdl:message>
  <wsdl:message name="GetMessageStatusSoapOut">
    <wsdl:part name="parameters" element="tns:GetMessageStatusResponse" />
  </wsdl:message>
  <wsdl:message name="SendSchaduleMessageSoapIn">
    <wsdl:part name="parameters" element="tns:SendSchaduleMessage" />
  </wsdl:message>
  <wsdl:message name="SendSchaduleMessageSoapOut">
    <wsdl:part name="parameters" element="tns:SendSchaduleMessageResponse" />
  </wsdl:message>
  <wsdl:message name="SendCourseMessageSoapIn">
    <wsdl:part name="parameters" element="tns:SendCourseMessage" />
  </wsdl:message>
  <wsdl:message name="SendCourseMessageSoapOut">
    <wsdl:part name="parameters" element="tns:SendCourseMessageResponse" />
  </wsdl:message>
  <wsdl:message name="GetCreditSoapIn">
    <wsdl:part name="parameters" element="tns:GetCredit" />
  </wsdl:message>
  <wsdl:message name="GetCreditSoapOut">
    <wsdl:part name="parameters" element="tns:GetCreditResponse" />
  </wsdl:message>
  <wsdl:message name="GetReciveMessageSoapIn">
    <wsdl:part name="parameters" element="tns:GetReciveMessage" />
  </wsdl:message>
  <wsdl:message name="GetReciveMessageSoapOut">
    <wsdl:part name="parameters" element="tns:GetReciveMessageResponse" />
  </wsdl:message>
  <wsdl:portType name="TrezSmsServiceSoap">
    <wsdl:operation name="SendMessage">
      <wsdl:input message="tns:SendMessageSoapIn" />
      <wsdl:output message="tns:SendMessageSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetMessageStatus">
      <wsdl:input message="tns:GetMessageStatusSoapIn" />
      <wsdl:output message="tns:GetMessageStatusSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SendSchaduleMessage">
      <wsdl:input message="tns:SendSchaduleMessageSoapIn" />
      <wsdl:output message="tns:SendSchaduleMessageSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SendCourseMessage">
      <wsdl:input message="tns:SendCourseMessageSoapIn" />
      <wsdl:output message="tns:SendCourseMessageSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetCredit">
      <wsdl:input message="tns:GetCreditSoapIn" />
      <wsdl:output message="tns:GetCreditSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetReciveMessage">
      <wsdl:input message="tns:GetReciveMessageSoapIn" />
      <wsdl:output message="tns:GetReciveMessageSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="TrezSmsServiceSoap" type="tns:TrezSmsServiceSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="SendMessage">
      <soap:operation soapAction="http://tempuri.org/SendMessage" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetMessageStatus">
      <soap:operation soapAction="http://tempuri.org/GetMessageStatus" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendSchaduleMessage">
      <soap:operation soapAction="http://tempuri.org/SendSchaduleMessage" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendCourseMessage">
      <soap:operation soapAction="http://tempuri.org/SendCourseMessage" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCredit">
      <soap:operation soapAction="http://tempuri.org/GetCredit" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReciveMessage">
      <soap:operation soapAction="http://tempuri.org/GetReciveMessage" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="TrezSmsServiceSoap12" type="tns:TrezSmsServiceSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="SendMessage">
      <soap12:operation soapAction="http://tempuri.org/SendMessage" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetMessageStatus">
      <soap12:operation soapAction="http://tempuri.org/GetMessageStatus" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendSchaduleMessage">
      <soap12:operation soapAction="http://tempuri.org/SendSchaduleMessage" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SendCourseMessage">
      <soap12:operation soapAction="http://tempuri.org/SendCourseMessage" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCredit">
      <soap12:operation soapAction="http://tempuri.org/GetCredit" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetReciveMessage">
      <soap12:operation soapAction="http://tempuri.org/GetReciveMessage" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="TrezSmsService">
    <wsdl:port name="TrezSmsServiceSoap" binding="tns:TrezSmsServiceSoap">
      <soap:address location="http://raygansms.com/TrezSmsService.asmx" />
    </wsdl:port>
    <wsdl:port name="TrezSmsServiceSoap12" binding="tns:TrezSmsServiceSoap12">
      <soap12:address location="http://raygansms.com/TrezSmsService.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>