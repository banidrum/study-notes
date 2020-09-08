# Notes on Kubernetes

I'll be writing the notes based on a book that I'm reading called "Kubernetes - tudo sobre orquestração de contêineres".

## Containers:

- A container can be seen as an abstraction from the infrastructure, that acts on the virtualization in the operating system level.
- A container basically is a process managed by a container engine, that acts between the processes and the OS.
- Containers are "running images".
- Containers are great to deal with microservices.
- We can use versioning on our images, which makes them more resilient.
- Images are stored on Containers Registries. A container registry is a public (or private) repository. Dockers uses those registries to download the images.

## Kubernetes:

Just an overview on Kubernetes.

Kubernetes is an open source system for automation, management, scalability and deployment of container based applications.

Kubernetes does all the automation process. It is responsible for creating the containers, manage them, keep the infrastructure running, and, when a container fails, it's also its job to create a new instance, maintaining the ideal cluster state.

The master node must be the most resilient, so its job is to manage the containers, not execute them.

Workload is everything that is running inside a node.

Kubernetes has kubectl for commands (short name for Kube Control), it is a set of REST calls, because Kubernetes has a RESTful API that is served by the master node.

- Kubelet

    Kubelets are responsible for receiving the commands and executing them on their own machines.

    They are called **primary node agents.**

    The kubelet also guarantees information like:

    - The machine which the node is running is healthy.
    - The containers which are running in this node are healthy.

    To guarantee this information, the kubelet does a health check periodically.

Context is the name that is given to the file that has the connection data for a Kubernetes cluster.

Kubernetes is multicontext, it can be configured to connect in multiple clusters, and after we can change the cluster which we want to execute the command.

# Kubernetes in the cloud:

The cloud providers already have some configuration out of the box, and they offer managed clusters, making the Kubernetes configuration easier.

Vendor coupling/lock is a disadvantage.

# Kubernetes for real:

The smallest structure present in Kubernetes is called pod.

A pod is an abstraction for a process running in your cluster, like a Web server for an API. It can be easily seen as a grouping of containers.

- To create a pod with a image, run *kubectl run*
- To show which pods are running, run *kubectl get pods*
- To check the pods logs, run *kubectl logs <pod>,* you can add a flag to the command, like —tail

There are two ways to create a pod:

- The imperative way (using kubectl in the command line)
- The declarative way (using a YAML file)

The declarative way is better, because we can apply version control on ours infrastructure files, making it highly replicable.

- To create the pod using our YAML file, run *kubectl create -f <file>*
- We can execute the *create* command as many times as we want, and if the file is the same, we will always get the same result. This is called **infrastructure immutability**.

## Pod lifecycle

A pod is a disposable unit from Kubernetes, like any other workload that doesn't has connect volumes. This means that the pod is ephemeral.

Any work done on the pod remains on it, until the pod is removed, when this happens, all the pod content and data are lost.

Containers must be self contained. It means that we don't have the state coupled with our logic, everything that we must store is in another place. A lot of pods connected in a single database, for instance.

A pod has 5 phases that defines the current pod status: pending, running, suceeded, failed and unknown.

## Services

Services are defined as a logical pod set and by a policy which we will know how we will access these pods.

Labels are the only way to group pods by a set. We define in which set a pod will be placed.

- Tip:

    The *apply* command is used to update already existing structures,

    The *create* command is used to create new structures.

Labels aren't unique, it's expected that the same label will be used for different services.

Labels can also be used as a selector to execute commands in the terminal with *kubectl -l,*

where -l is the label.

## Creating a network

Kubernetes is a container manager, so it doesn't comes with a network architecture out of the box.

To expose our services, we need that all the users connect through a single address in an entrance port (gateway). This port will be a reverse proxy with NginX.

## Ingress

Edge routers:

An edge router allows networks that don't know each other to connect. It can be a device, or a configuration in a default router. It is responsible to connect internal networks to outside networks.

K8S has an internal network between its workloads. By default, any K8S services will only be routed inside the cluster internal network.

What *ingress* basically does is saying who can access what through a set of specific rules, using a DNS.

## Persisting data with volumes

All workloads of the *pod* type are ephemeral, so, everytime a pod is removed or reseted, its content is lost.

In some cases, we will need to persist some data.

We can use volumes to store data.

Volume is a directory created inside the container in mounting time.

It's important to be careful with volumes, because depending on the volume type, the data can be easily lost.

## Persistent volumes

A persistent volume is a storage directory provisioned on the cluster. PV's have a completely independent lifecycle from the structure that the pod is using.

It's a more robust and more secure way to store your data. You can use it to create a database or to store content that must be persistent, because the volume won't be destroy at the end of the execution.
