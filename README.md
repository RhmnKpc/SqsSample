# SqsSample
Birden fazla sqs listener ile çalışan örnek proje.
Bir listener aynı anda bir tane mesajı çekerken diğeri async olarak aynı anda birden fazla mesajı çekiyor.
SimpleMessageListenerContainerFactory de setMaxNumberOfMessages 1 olarak set edilmesine rağmen
queueone listenerinde geçilen annotationlardan dolayı aynı anda birden fazla mesajı çekebiliyor.
