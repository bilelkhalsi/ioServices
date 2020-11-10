# Kind K8s Cluster Creation


### Requirements

Before you begin the following cluster creation steps, you should have :
* [Docker](https://docs.docker.com/get-docker) installed and running
* [Sigs Kind](https://kind.sigs.k8s.io/) installed
* [Kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/) command line installed.

### K8s cluster creation

Ensure Kind command line is installed.
```
 kind version
```
Create new cluster named ``Kind`` using the config file ``kind.yaml`` in the current folder.  

/!\ As set in the config yaml, kind will bind to three of your host port numbers : 31370, 31380 and 31390.
Before you create the cluster, please check they are not already used.
```
 kind create cluster --config=kind.yaml
```

Check this cluster is created:
```
 kind get clusters #Should return the newly created cluster : Kind
```

### Set ```kubectl``` context

```
kubectl cluster-info --context kind
```


### Reference Documentation
For further reference, please consider the following sections:

* [Kind Documentation](https://kind.sigs.k8s.io/)
* [Docker Documentation](https://docs.docker.com/get-docker) 

