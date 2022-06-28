function G = Gaussien(img,sigma)
  
  G = (1/(sigma*sqrt(2*pi)))*exp(-((img)^2)/(2*sigma^2))