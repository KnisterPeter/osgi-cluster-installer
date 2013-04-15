package de.matrixweb.osgi.cluster.installer.internal;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import de.matrixweb.osgi.kernel.maven.Installer;

/**
 * @author markusw
 */
public class InstallerTracker extends ServiceTracker<Installer, Installer> {

  private final ClusterInstaller clusterInstaller;

  /**
   * @param context
   * @param clusterInstaller
   */
  public InstallerTracker(final BundleContext context,
      final ClusterInstaller clusterInstaller) {
    super(context, Installer.class, null);
    this.clusterInstaller = clusterInstaller;
  }

  /**
   * @see org.osgi.util.tracker.ServiceTracker#addingService(org.osgi.framework.ServiceReference)
   */
  @Override
  public Installer addingService(final ServiceReference<Installer> reference) {
    final Installer installer = super.addingService(reference);
    this.clusterInstaller.setInstaller(installer);
    return installer;
  }

  /**
   * @see org.osgi.util.tracker.ServiceTracker#modifiedService(org.osgi.framework.ServiceReference,
   *      java.lang.Object)
   */
  @Override
  public void modifiedService(final ServiceReference<Installer> reference,
      final Installer service) {
    this.clusterInstaller.setInstaller(this.context.getService(reference));
  }

  /**
   * @see org.osgi.util.tracker.ServiceTracker#removedService(org.osgi.framework.ServiceReference,
   *      java.lang.Object)
   */
  @Override
  public void removedService(final ServiceReference<Installer> reference,
      final Installer service) {
    this.clusterInstaller.setInstaller(null);
    super.removedService(reference, service);
  }

}
