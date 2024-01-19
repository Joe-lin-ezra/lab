rem : create a private key
rem : $ openssl genrsa -out DevUser.key 2048
openssl genrsa -out <name>.key/<name>.pem 2048

rem : create certificate signature request
rem : $ openssl req -new -key DevUser.key -out DevUser.csr -subj "/CN=DevUser/O=development"
rem : ! common name would be ref-ed by role-binding
rem : 	> https://kubernetes.io/docs/reference/access-authn-authz/authentication/#x509-client-certs
openssl req -new -key <previous file> -out <name>.csr -subj "/CN=<common name>/O=<group>"

rem : sign the csr
rem : $ openssl x509 -req -in DevUser.csr -CA ${HOME}/.minikube/ca.crt -CAkey ${HOME}/.minikube/ca.key  -CAcreateserial -out DevUser.crt -days 45
openssl x509 -req -in <previous>.csr -CA ca.crt -CAkey ca.key  -CAcreateserial -out <cert name>.crt/<cert name>.pem -days 45

rem : new user to kube
rem : $ kubectl config set-credentials DevUser --client-certificate ${HOME}/.kube/DevUser.crt --client-key ${HOME}/.kube/DevUser.key
kubectl config set-credentials <user name> --client-certificate <previous>.crt --client-key <step 1 file>

rem : new context to kube
rem : $ kubectl config set-context DevUser-context --cluster=minikube --namespace=development --user=DevUser
kubectl config set-context <context name> --cluster=<cluster name> --namespace=<namespace> --user=<previous user>

