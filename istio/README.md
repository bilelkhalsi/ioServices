# Istio on KinD cluster.

This task guide you to install istio on a KinD cluster.
### Requirements

Before you begin the next steps, you will need a Kubernetes cluster running, please follow the KinD guide to create new cluster.
You will need also [Helm 3](https://helm.sh/) installed on your machine.

### Install Istio-operator using Helm

* With kinD cluster created and Helm 3, you can go ahead and download istio [latest release](https://github.com/istio/istio/releases/tag/1.7.4) (1.7.4 in writing time).
* Unzip and open new terminal in istio-1.7.4 folder.
* Change directory to ``istio-operator`` folder under ``manifests/charts``
* Then install using Helm : ``helm install istio-operator istio-operator/``

Before you go ahead and install istio, make sure istio-operator is ready :

```
kubectl get all -n istio-operator # you should see istio-operator pod ready and runnig

``` 
### Install Istio
With istio-operator ready and running, you are ready to install istio:

```
kubectl create ns istio-system # create new namespace for istio.

```
```
kubectl apply -f istio.yaml # This will install istio in instio-system namespace.

```

Ensure Istio is ready and running.

```
kubectl get all -n istio-system # You should see all istio resources ready and running

```
### Activate istio auto-injection 

Istio need to be activated/injected on each namespace in which you need istio.
To activate istio on ``default`` namespace:

```
kubectl label namespace default istio-injection=enabled
```

### Reference Documentation
For further reference, please consider the following sections:

* [Istio website](https://istio.io/latest/)


