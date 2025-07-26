# Kubernetes Pod Restart (Local)

Kod deyisikliyinden sonra podu restart etmek lazimdi  

## 1️ Deployment və Pod-ları Yoxlamaq
Əvvəlcə hansı deployment və pod-ların işlədiyini yoxlamaq:
```bash
kubectl get deployments
kubectl get pods
```
## 2 Pod restart 
```bash
kubectl rollout restart deployment springboot-app
```


