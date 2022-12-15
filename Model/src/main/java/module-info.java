module model {
    requires org.apache.commons.lang3;
    requires lombok;
    exports model.field;
    exports model.exceptions;
    opens model;
    exports model;
    opens dao;
    exports dao;

}