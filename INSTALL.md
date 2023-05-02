## Installation Steps:

Helm charts are provided inside https://github.com/eclipse-tractusx/data-exchange-test-service

1.) Using helm:  <br />
    helm install ReleaseName ChartName
    
    a.) Add helm repository in tractusx:
           helm repo add data-exchange https://eclipse-tractusx.github.io/charts/tractusx-dev
    b.) To search the specific repo in helm repositories 
           helm search repo tractusx-dev
    c.) To install using helm command:  
           helm install data-exchange tractusx-dev/data-exchange


2.) Local installation:

    a.) git clone https://github.com/eclipse-tractusx/data-exchange-test-service.git
    b.) Modify values file according to your requirement
    c.) You need to define the secrets as well in values.yaml
        secret:
          edchostname:  -> EDC connector Hostname 
          edcapikeyheader:   -> Header for EDC
          edcapikey:   -> key for EDC API
          e2edetsurl:  -> End to end data exchange service URL

    d.) These secrets should be defined in Hashicorp vault
    e.) Deploy in a kubernetes cluster
        helm install data-exchange charts/data-exchange/ -n NameSpace
